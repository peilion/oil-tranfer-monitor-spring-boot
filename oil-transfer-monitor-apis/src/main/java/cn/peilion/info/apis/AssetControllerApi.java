package cn.peilion.info.apis;

import cn.peilion.model.common.dtos.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author fpl11
 */
public interface AssetControllerApi {

    ResponseResult getCards();

    ResponseResult getCard(Integer id);

    ResponseResult getDetail(Integer id);

    ResponseResult getStatistic(String rule);

    ResponseResult getCardWithLog(Integer id) throws JsonProcessingException;
}
