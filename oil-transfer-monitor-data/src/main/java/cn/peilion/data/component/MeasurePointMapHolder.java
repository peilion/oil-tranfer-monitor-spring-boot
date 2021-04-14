package cn.peilion.data.component;

import cn.peilion.model.common.dtos.MeasurePointListDto;
import cn.peilion.model.mappers.MeasurePointMapper;
import cn.peilion.model.pojos.measurePoint.MeasurePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 测点及数据表的路由关系保持组件
 * Created by peilion on 2021/04/06.
 */
@Component
public class MeasurePointMapHolder {

    @Autowired
    private MeasurePointMapper measurePointMapper;

    private static HashMap<Integer, MeasurePoint> measurePointMap = new HashMap<>();
    private static HashMap<Integer, ArrayList> assetMeasurePointMap = new HashMap<>();

    @PostConstruct
    public void initMeasurePointMap() {
        List<MeasurePoint> measurePoints = measurePointMapper.loadMeasurePointList(new MeasurePointListDto());
        measurePoints.forEach(measurePoint -> {
            measurePointMap.put(measurePoint.getId(), measurePoint);
            assetMeasurePointMap.putIfAbsent(measurePoint.getAssetId(), new ArrayList());
            ArrayList measurePointIdList = assetMeasurePointMap.get(measurePoint.getAssetId());
            measurePointIdList.add(measurePoint.getId());
        });

    }

    public MeasurePoint getCachedMeasurePoint(Integer id) {
        return measurePointMap.get(id);
    }
}
