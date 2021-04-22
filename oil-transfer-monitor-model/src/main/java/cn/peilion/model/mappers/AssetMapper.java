package cn.peilion.model.mappers;

import cn.peilion.model.pojos.organization.TreeItem;
import cn.peilion.model.pojos.asset.AssetCard;
import cn.peilion.model.pojos.asset.AssetDetail;
import cn.peilion.model.pojos.asset.AssetGeoStat;
import cn.peilion.model.pojos.SingleFiledStatisticResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetMapper {
    List<AssetCard> loadAssetCards(@Param("id") Integer id);

    AssetDetail loadAssetDetail(@Param("id") Integer id);

    List<SingleFiledStatisticResult> loadAssetStatByStatu();

    List<SingleFiledStatisticResult> loadAssetStatByType();

    List<SingleFiledStatisticResult> loadAssetStatByAverageHealthIndicator();

    List<AssetGeoStat> loadGeoAssetStat();

    List<TreeItem> loadTreeItems();
}
