package cn.peilion.data.service;

import cn.peilion.model.common.dtos.VibrationFeatureDto;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.dtos.VibrationFeatureListDto;

public interface VibrationFeatureService {
    ResponseResult getById(VibrationFeatureDto dto, Integer measurePointId);

    ResponseResult getList(VibrationFeatureListDto dto, Integer measurePointId) throws NoSuchFieldException, IllegalAccessException;
}
