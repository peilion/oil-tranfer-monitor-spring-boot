package cn.peilion.data.apis;

import cn.peilion.model.common.dtos.MSETDto;
import cn.peilion.model.common.dtos.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MSETControllerApi {
    ResponseResult getSimilarityAndThresholdList(MSETDto dto, Integer id);

    ResponseResult getEstimationList(MSETDto dto, Integer id) throws JsonProcessingException;

    ResponseResult getEstimationById(Integer dataId, Integer id);

}
