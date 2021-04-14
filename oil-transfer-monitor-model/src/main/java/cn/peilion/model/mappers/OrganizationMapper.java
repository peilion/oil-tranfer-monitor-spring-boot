package cn.peilion.model.mappers;

import cn.peilion.model.pojos.organization.Station;
import cn.peilion.model.pojos.organization.TreeItem;

import java.util.List;

public interface OrganizationMapper {
    List<TreeItem> loadStationTreeItems();

    List<TreeItem> loadBranchCompanyTreeItems();

    List<TreeItem> loadRegionCompanyTreeItems();

    List<Station> loadStationWeather();
}
