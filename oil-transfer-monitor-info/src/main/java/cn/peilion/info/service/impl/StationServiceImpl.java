package cn.peilion.info.service.impl;

import cn.peilion.info.service.StationService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.mappers.AssetMapper;
import cn.peilion.model.mappers.OrganizationMapper;
import cn.peilion.model.pojos.organization.TreeItem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fpl11
 */
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private OrganizationMapper organizationMapper;

    private final String[] COLOR_ARRAY = new String[]{"#1a8bff", "#51a2f7", "#79b8ff", "#93ccff"};
    private final Integer[] SIZE_ARRAY = new Integer[]{130, 80, 60, 40};

    @Override
    public ResponseResult getStationTree() {

        List<TreeItem> branchCompanyTreeItems = organizationMapper.loadBranchCompanyTreeItems();
        List<TreeItem> regionCompanyTreeItems = organizationMapper.loadRegionCompanyTreeItems();
        List<TreeItem> stationTreeItems = organizationMapper.loadStationTreeItems();
        List<TreeItem> assetTreeItems = assetMapper.loadTreeItems();
//        CompletableFuture 异步查询数据库，这个场景下没什么用
//        CopyOnWriteArrayList<TreeItem> arrayList = new CopyOnWriteArrayList<>();
//        CompletableFuture<List<TreeItem>> queryFromBC = CompletableFuture.supplyAsync(() -> organizationMapper.loadBranchCompanyTreeItems()).whenCompleteAsync((s, throwable) -> arrayList.addAll(s));
//        CompletableFuture<List<TreeItem>> queryFromRC = CompletableFuture.supplyAsync(() -> organizationMapper.loadRegionCompanyTreeItems()).whenCompleteAsync((s, throwable) -> arrayList.addAll(s));
//        CompletableFuture<List<TreeItem>> queryFromST = CompletableFuture.supplyAsync(() -> organizationMapper.loadStationTreeItems()).whenCompleteAsync((s, throwable) -> arrayList.addAll(s));
//        CompletableFuture<List<TreeItem>> queryFromAS = CompletableFuture.supplyAsync(() -> assetMapper.loadTreeItems()).whenCompleteAsync((s, throwable) -> arrayList.addAll(s));
//        CompletableFuture<Void> all = CompletableFuture.allOf(queryFromBC, queryFromRC, queryFromAS, queryFromST);
//        all.join();
        List<TreeItem> treeItems = Stream.of(branchCompanyTreeItems, regionCompanyTreeItems, stationTreeItems, assetTreeItems).flatMap(Collection::stream).collect(Collectors.toList());
        List<TreeItem> collect = treeItems.stream().filter(item -> item.getLevel().equals(0)).map(item -> covert(item, treeItems)).collect(Collectors.toList());
        return ResponseResult.okResult(collect);
    }

    private TreeItem covert(TreeItem item, List<TreeItem> itemList) {
        TreeItem node = new TreeItem();
        BeanUtils.copyProperties(item, node);
        List<TreeItem> children = itemList.stream()
                .filter(child -> child.getParentId().equals(item.getId()) && child.getLevel().equals(item.getLevel() + 1))
                .map(child -> covert(child, itemList)).collect(Collectors.toList());
        node.setChildren(children);
        node.setColor(COLOR_ARRAY[item.getLevel()]);
        node.setSize(SIZE_ARRAY[item.getLevel()]);
        return node;
    }

    @Override
    public ResponseResult listStationWeather() {
        return ResponseResult.okResult(organizationMapper.loadStationWeather());
    }
}
