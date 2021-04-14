package cn.peilion.data.service.impl;

import cn.peilion.data.component.MeasurePointMapHolder;
import cn.peilion.data.service.ElectricFeatureService;
import cn.peilion.model.common.dtos.ElectricFeatureDto;
import cn.peilion.model.common.dtos.ElectricFeatureListDto;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.enums.AppHttpCodeEnum;
import cn.peilion.model.mappers.ElectricFeatureMapper;
import cn.peilion.model.pojos.electric.ElectricFeature;
import cn.peilion.model.pojos.measurePoint.MeasurePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class ElectricFeatureServiceImpl implements ElectricFeatureService {

    @Autowired
    private ElectricFeatureMapper electricFeatureMapper;
    @Autowired
    private MeasurePointMapHolder measurePointMapHolder;

    @Override
    public ResponseResult getById(ElectricFeatureDto dto, Integer measurePointId) {
        MeasurePoint measurePoint = measurePointMapHolder.getCachedMeasurePoint(measurePointId);
        Boolean isValid = checkMeasurePoint(measurePoint);
        if (!isValid) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "测点id或类型不合法");

        }
        ElectricFeature electricFeature = electricFeatureMapper.loadFeature(dto, measurePoint.getStationId(), measurePoint.getInnerStationId());

        return ResponseResult.okResult(electricFeature);
    }

    @Override
    public ResponseResult getList(ElectricFeatureListDto dto, Integer measurePointId) throws NoSuchFieldException, IllegalAccessException {
        MeasurePoint measurePoint = measurePointMapHolder.getCachedMeasurePoint(measurePointId);
        Boolean isValid = checkMeasurePoint(measurePoint);
        if (!isValid) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "测点id或类型不合法");

        }
        List<ElectricFeature> featureList = electricFeatureMapper.loadElectricFeatureList(dto, measurePoint.getStationId(), measurePoint.getInnerStationId());

        HashMap<String, Object> res = new HashMap<>();

        ArrayList<String> arrayList = new ArrayList<>(dto.getFileds().length);
        Collections.addAll(arrayList, dto.getFileds());
        arrayList.add("time");
        arrayList.add("id");

        if (featureList != null) {
            Class cls = featureList.get(0).getClass();

            for (String filed : arrayList) {
                Field declaredField = cls.getDeclaredField(filed);
                declaredField.setAccessible(true);
                ArrayList<Object> filedList = new ArrayList<>(featureList.size());
                for (ElectricFeature feature : featureList) {
                    Object o = declaredField.get(feature);
                    filedList.add(o);
                }
                res.put(filed, filedList);
            }
            return ResponseResult.okResult(res);
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
    }

    private Boolean checkMeasurePoint(MeasurePoint measurePoint) {
        return measurePoint != null && measurePoint.getType().equals(1);
    }
}
