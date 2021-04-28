"""
该任务涉及插入表： MsetWarningLog，AssetHI
涉及更新表：Asset
"""
import datetime

import numpy as np

from db import session_make
from db.conn_engine import meta_engine
from db_model import PumpUnit, AssetHI, MeasurePoint, Asset, Logs
from services.MSET.core import (
    Temp_MemMat,
    mset_estimate,
    calculate_similarity,
    threshold_caculate,
)
from hashlib import md5


def fetch_pumps(session):
    pumps = (
        session.query(
            PumpUnit.asset_id, PumpUnit.mset_model_path, Asset.name.label("name")
        )
            .filter(PumpUnit.mset_model_path != None)
            .join(Asset, Asset.id == PumpUnit.asset_id)
            .all()
    )

    return pumps


def fetch_mps(session, asset_id):
    mps = (
        session.query(
            MeasurePoint.name, MeasurePoint.station_id, MeasurePoint.inner_station_id
        )
            .filter(MeasurePoint.asset_id == asset_id, MeasurePoint.type == 0)
            .order_by(MeasurePoint.inner_station_id)
            .all()
    )

    return mps


def fetch_base_data(session, cycle_number, base_mp, asset_id):
    data = session.execute(
        "SELECT d.id as id, d.time as time, d.rms as rms "
        "from vib_data_{0}_{1} as d "
        "LEFT JOIN asset_hi_{2} as h on d.id = h.data_id "
        "where h.data_id is null "
        "order by d.id "
        "limit {3};".format(
            base_mp.station_id, base_mp.inner_station_id, asset_id, cycle_number
        )
    )

    return data.fetchall()


def fetch_feature_matrix(session, base_data_list, mps):
    feature_matrix = []
    for base_data in base_data_list:
        feature_row = [base_data["rms"]]
        for mp in mps[1:]:
            query = "select rms from vib_data_{0}_{1} order by abs(datediff(time,'{2}')) limit 1".format(
                mp.station_id, mp.inner_station_id, base_data["time"]
            )
            res = session.execute(query)
            res = res.fetchall()
            feature_row.append(res[0]["rms"])
        feature_matrix.append(feature_row)
    feature_matrix = np.array(feature_matrix)
    return feature_matrix


def evaluate(path, feature_matrix):
    memory_mat = np.load(path)
    feature_matrix_max = memory_mat[-2, :]
    feature_matrix_min = memory_mat[-1, :]
    memory_mat = memory_mat[:-2, :]
    temp_memory_mat = Temp_MemMat(memory_mat)
    feature_matrix = (feature_matrix - feature_matrix_min) / (
            feature_matrix_max - feature_matrix_min
    )
    Kest = mset_estimate(
        memorymat=memory_mat, Kobs=feature_matrix, Temp=temp_memory_mat
    )

    sim = calculate_similarity(feature_matrix, Kest)
    thres, warning_index = threshold_caculate(sim)
    Kest = Kest * (feature_matrix_max - feature_matrix_min) + feature_matrix_min
    return sim, thres, Kest, warning_index


def determine_statu(feature_matrix):
    if feature_matrix[-1][0] < 0.2:
        return [4 for _ in feature_matrix[-1]]
    else:
        res = []
        for item in feature_matrix[-1]:
            res.append(np.searchsorted([2.8, 7.1, 18], item))
        return res


def mset_evaluate(cycle_number):
    estimate_count = 0
    session = session_make(engine=meta_engine)
    pumps = fetch_pumps(session)
    for pump in pumps:
        asset_hi_model = AssetHI.model(point_id=pump.asset_id)
        mps = fetch_mps(session=session, asset_id=pump.asset_id)

        if len(mps) > 0:
            base_data_list = fetch_base_data(
                session=session,
                cycle_number=cycle_number,
                base_mp=mps[0],
                asset_id=pump.asset_id,
            )
            if len(base_data_list) == cycle_number:
                feature_matrix = fetch_feature_matrix(
                    session=session, base_data_list=base_data_list, mps=mps
                )
                sim, thres, Kest, warning_index = evaluate(
                    path=pump.mset_model_path, feature_matrix=feature_matrix
                )

                evaluate_res_insert_value = []

                for i in range(len(base_data_list)):
                    evaluate_res_insert_value.append(
                        asset_hi_model(
                            health_indicator=float(sim[i][0] * 100),
                            similarity=float(sim[i][0]),
                            threshold=float(thres[i][0]),
                            time=base_data_list[i]["time"],
                            data_id=base_data_list[i]["id"],
                            est={
                                "label": [mp.name for mp in mps],
                                "raw": feature_matrix[i].tolist(),
                                "est": Kest[i].tolist(),
                            },
                        )
                    )
                try:
                    for index, row in enumerate(evaluate_res_insert_value):
                        session.add(row)
                        session.commit()
                        if len(warning_index) != 0:
                            if index in warning_index:

                                pre_log_code = session.query(Logs.md_code). \
                                    order_by(Logs.time.desc()). \
                                    filter(Logs.asset_id == pump.asset_id, Logs.type == 'mset').limit(1).all()
                                warning_mp = mps[np.argmax(feature_matrix[index] - Kest[index])]
                                description = warning_mp.name + "异常。"
                                this_log_code = md5(description.encode(encoding='UTF-8')).hexdigest()

                                if len(pre_log_code) == 0 or pre_log_code[0].md_code != this_log_code:
                                    session.add(Logs(type='mset',
                                                     time=base_data_list[index]["time"],
                                                     asset_id=pump.asset_id,
                                                     description=description,
                                                     mp_name=warning_mp.name,
                                                     detail={'reporter_id': row.id},
                                                     md_code=this_log_code))
                                    session.commit()
                    level_mapper = {
                        1: '轻度',
                        2: '较严重',
                        3: '严重'
                    }
                    status = determine_statu(feature_matrix=feature_matrix)
                    description = ''
                    danger_mp = ''
                    for index, statu in enumerate(status):
                        if statu != 4 and statu != 0:
                            description += mps[index].name + '均方根值' + level_mapper[statu] + '超标;'
                            danger_mp += (mps[index].name) + ';'
                    this_log_code = md5(description.encode(encoding='UTF-8')).hexdigest()
                    pre_log_code = session.query(Logs.md_code). \
                        order_by(Logs.time.desc()). \
                        filter(Logs.asset_id == pump.asset_id, Logs.type == 'vib').limit(1).all()
                    if len(pre_log_code) == 0 or pre_log_code[0].md_code != this_log_code:
                        session.add(Logs(type='vib',
                                         time=base_data_list[-1]["time"],
                                         asset_id=pump.asset_id,
                                         description=description,
                                         mp_name=danger_mp,
                                         detail={'rms': [float(item) for item in feature_matrix[-1]],
                                                 'mps': [mp.name for mp in mps],
                                                 'severity': max(status)},
                                         md_code=this_log_code))
                        session.commit()

                    session.query(Asset).filter(Asset.id == pump.asset_id).update(
                        {
                            "statu": max([statu for statu in status if statu != 4]),
                            "health_indicator": evaluate_res_insert_value[
                                -1
                            ].health_indicator,
                            "md_time": datetime.datetime.now(),
                        }
                    )
                    session.commit()
                    estimate_count += len(evaluate_res_insert_value)
                except Exception as e:
                    session.rollback()
                    print(e)

    session.close()

    return estimate_count


if __name__ == "__main__":
    for i in range(15):
        estimate_count = mset_evaluate(3)
