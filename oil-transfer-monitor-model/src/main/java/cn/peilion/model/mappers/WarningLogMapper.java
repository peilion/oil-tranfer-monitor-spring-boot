package cn.peilion.model.mappers;

import cn.peilion.model.pojos.SingleFiledStatisticResult;
import cn.peilion.model.pojos.configuration.MaintenanceSuggestion;
import cn.peilion.model.pojos.log.AssetEvent;
import cn.peilion.model.pojos.log.WarningLog;
import cn.peilion.model.pojos.log.WarningLogTableItem;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WarningLogMapper {

    List<SingleFiledStatisticResult> loadStatisticByReadBehavior();

    Integer loadStatisticByPeriod(@Param("after") Date after, @Param("before") Date now);

    WarningLog loadById(Integer id);

    List<MaintenanceSuggestion> loadSuggestions(@Param("patterns") Set<Map.Entry<String, Integer>> patterns);

    List<WarningLogTableItem> loadList(Integer size);

    List<WarningLogTableItem> loadBriefList();

    List<AssetEvent> loadRecentAssetEvent(Integer assetId);
}
