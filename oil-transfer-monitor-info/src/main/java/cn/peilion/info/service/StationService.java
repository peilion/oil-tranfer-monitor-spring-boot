package cn.peilion.info.service;

import cn.peilion.model.common.dtos.ResponseResult;

public interface StationService {
    ResponseResult getStationTree();

    ResponseResult listStationWeather();
}
