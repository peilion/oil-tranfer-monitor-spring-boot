package cn.peilion.log.apis;

import cn.peilion.model.common.dtos.ResponseResult;

public interface WarningLogControllerApi {
    ResponseResult getStatistic(String rule);

    ResponseResult getWarningLogById(Integer id);

    ResponseResult getWarningList(Boolean brief);

    ResponseResult getAssetRecentEvent(Integer assetId);

}
