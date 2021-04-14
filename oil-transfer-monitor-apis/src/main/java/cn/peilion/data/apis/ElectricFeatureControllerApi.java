package cn.peilion.data.apis;

import cn.peilion.model.common.dtos.ElectricFeatureDto;
import cn.peilion.model.common.dtos.ElectricFeatureListDto;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.dtos.VibrationFeatureListDto;

public interface ElectricFeatureControllerApi {
    ResponseResult getById(ElectricFeatureDto dto, Integer measurePointId);

    ResponseResult getList(ElectricFeatureListDto dto, Integer measurePointId) throws NoSuchFieldException, IllegalAccessException;


}
