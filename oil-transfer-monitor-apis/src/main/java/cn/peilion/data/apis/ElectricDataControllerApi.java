package cn.peilion.data.apis;

import cn.peilion.model.common.dtos.DataDto;
import cn.peilion.model.common.dtos.ResponseResult;

import java.util.concurrent.ExecutionException;

public interface ElectricDataControllerApi {
    ResponseResult getById(DataDto dto, Integer measurePointId) throws ExecutionException, InterruptedException;
}
