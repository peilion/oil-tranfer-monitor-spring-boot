package cn.peilion.data.service;

import cn.peilion.model.common.dtos.DataDto;
import cn.peilion.model.common.dtos.ResponseResult;

import java.util.concurrent.ExecutionException;

public interface ElectricDataService {

    ResponseResult getById(DataDto dto, Integer measurePointId) throws InterruptedException, ExecutionException;

}
