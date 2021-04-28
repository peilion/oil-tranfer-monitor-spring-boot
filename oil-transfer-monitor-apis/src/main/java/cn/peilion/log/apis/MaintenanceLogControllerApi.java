package cn.peilion.log.apis;

import cn.peilion.model.common.dtos.ResponseResult;

/**
 * @author fpl11
 */
public interface MaintenanceLogControllerApi {
    /**
     * 维修记录统计
     *
     * @return ResponseResult
     */
    ResponseResult getStatistic();

}
