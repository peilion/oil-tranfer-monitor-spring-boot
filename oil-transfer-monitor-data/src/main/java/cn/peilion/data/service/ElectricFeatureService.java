package cn.peilion.data.service;

import cn.peilion.model.common.dtos.ElectricFeatureDto;
import cn.peilion.model.common.dtos.ElectricFeatureListDto;
import cn.peilion.model.common.dtos.ResponseResult;

public interface ElectricFeatureService {
    ResponseResult getById(ElectricFeatureDto dto, Integer measurePointId);

    ResponseResult getList(ElectricFeatureListDto dto, Integer measurePointId) throws NoSuchFieldException, IllegalAccessException;
}
