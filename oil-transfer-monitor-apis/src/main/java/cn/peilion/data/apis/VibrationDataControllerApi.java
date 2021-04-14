package cn.peilion.data.apis;

import cn.peilion.model.common.dtos.DataDto;
import cn.peilion.model.common.dtos.ResponseResult;

public interface VibrationDataControllerApi {
    ResponseResult getById(DataDto dto, Integer measurePointId);
}
