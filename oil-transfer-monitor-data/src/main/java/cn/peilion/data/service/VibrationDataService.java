package cn.peilion.data.service;

import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.dtos.DataDto;

/**
 * @author fpl11
 */
public interface VibrationDataService {
    /**
     * 根据站场id及测点id，数据id返回对应数据条目
     *
     * @param dto 数据传输对象
     * @return
     */
    ResponseResult getById(DataDto dto, Integer measurePointId);
}
