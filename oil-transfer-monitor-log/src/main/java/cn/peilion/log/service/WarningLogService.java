package cn.peilion.log.service;

import cn.peilion.model.common.dtos.ResponseResult;

public interface WarningLogService {
    ResponseResult loadStatisticReport(String rule);

    ResponseResult loadById(Integer id);


    ResponseResult listWarningLog(Boolean brief);

    ResponseResult listAssetEvent(Integer assetId);
}
