package cn.peilion.info.apis;

import cn.peilion.model.common.dtos.ResponseResult;

public interface StationControllerApi {
    ResponseResult getStationTree();

    ResponseResult getStationWeather();

}
