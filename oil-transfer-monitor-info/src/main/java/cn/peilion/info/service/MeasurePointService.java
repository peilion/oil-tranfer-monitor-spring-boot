package cn.peilion.info.service;

import cn.peilion.model.common.dtos.MeasurePointListDto;
import cn.peilion.model.common.dtos.ResponseResult;

public interface MeasurePointService {
    ResponseResult loadById(Integer measurePointId);

    ResponseResult loadList(MeasurePointListDto dto);

    ResponseResult loadStat(String rule);
}
