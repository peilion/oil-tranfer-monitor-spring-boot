package cn.peilion.data.service;

import cn.peilion.model.common.dtos.MSETDto;
import cn.peilion.model.common.dtos.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MSETService {
    ResponseResult loadSimilarityAndThresholdList(MSETDto dto, Integer id);

    ResponseResult loadEstimation(MSETDto dto, Integer id) throws JsonProcessingException;

    ResponseResult loadEstimation(Integer id, Integer dataId) throws JsonProcessingException;
}
