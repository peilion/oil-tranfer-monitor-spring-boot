package cn.peilion.model.mappers;

import cn.peilion.model.common.dtos.MSETDto;
import cn.peilion.model.pojos.asset.AssetHi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MSETMapper {
    List<AssetHi> loadSimilarityAndThreshold(@Param("asset_id") Integer id, @Param("dto") MSETDto dto);

    List<AssetHi> loadThresholdList(@Param("asset_id") Integer id, @Param("dto") MSETDto dto);
}
