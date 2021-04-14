package cn.peilion.info.service;

import cn.peilion.model.common.dtos.AssetListDto;
import cn.peilion.model.common.dtos.ResponseResult;

/**
 * @author fpl11
 */
public interface AssetService {

    ResponseResult loadCards();

    ResponseResult loadCard(Integer id);

    ResponseResult loadDetail(Integer id);

    ResponseResult loadStat(String rule);
}
