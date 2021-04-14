package cn.peilion.data.apis;

import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.dtos.VibrationFeatureDto;
import cn.peilion.model.common.dtos.VibrationFeatureListDto;

public interface VibrationFeatureControllerApi {
    ResponseResult get(VibrationFeatureDto dto, Integer measurePointId);

    ResponseResult getList(VibrationFeatureListDto dto, Integer measurePointId) throws NoSuchFieldException, IllegalAccessException;
}
