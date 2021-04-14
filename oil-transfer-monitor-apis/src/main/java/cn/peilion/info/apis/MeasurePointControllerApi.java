package cn.peilion.info.apis;

import cn.peilion.model.common.dtos.MeasurePointListDto;
import cn.peilion.model.common.dtos.ResponseResult;

public interface MeasurePointControllerApi {
    ResponseResult getById(Integer measurePointId);

    ResponseResult getList(MeasurePointListDto dto);

    ResponseResult getStat(String rule);

}
