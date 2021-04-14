package cn.peilion.info.service.impl;

import cn.peilion.info.service.AssetService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.enums.AppHttpCodeEnum;
import cn.peilion.model.common.enums.StatuEnum;
import cn.peilion.model.mappers.AssetMapper;
import cn.peilion.model.pojos.asset.AssetCard;
import cn.peilion.model.pojos.asset.AssetDetail;
import cn.peilion.model.pojos.asset.AssetGeoStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public ResponseResult loadCards() {
        List<AssetCard> assetCards = assetMapper.loadAssetCards(null);
        return ResponseResult.okResult(assetCards);
    }

    @Override
    public ResponseResult loadCard(Integer id) {
        List<AssetCard> assetCards = assetMapper.loadAssetCards(id);
        if (assetCards.size() == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        return ResponseResult.okResult(assetCards);
    }

    @Override
    public ResponseResult loadDetail(Integer id) {
        AssetDetail assetDetail = assetMapper.loadAssetDetail(id);
        if (assetDetail == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        return ResponseResult.okResult(assetDetail);
    }

    @Override
    public ResponseResult loadStat(String rule) {
        switch (rule) {
            case "statu":
                return ResponseResult.okResult(assetMapper.loadAssetStatByStatu());
            case "type":
                return ResponseResult.okResult(assetMapper.loadAssetStatByType());
            case "health_indicator":
                return ResponseResult.okResult(assetMapper.loadAssetStatByAverageHealthIndicator());
            case "geo":
                List<AssetGeoStat> assetGeoStats = assetMapper.loadGeoAssetStat();
                HashMap<String, HashMap<String, Object>> res = new HashMap<>();
                for (AssetGeoStat assetGeoStat : assetGeoStats) {
                    if (res.get(assetGeoStat.getStationName()) == null) {
                        res.put(assetGeoStat.getStationName(), new HashMap<>());
                        HashMap<String, Object> map = res.get(assetGeoStat.getStationName());
                        map.put("latitude",assetGeoStat.getLatitude());
                        map.put("longitude",assetGeoStat.getLongitude());
                        map.put("statu_statistic", new ArrayList<>());
                        List statu_statistic = (List)  map.get("statu_statistic");
                        statu_statistic.add(new Integer[]{assetGeoStat.getStatu(),assetGeoStat.getCount()});
                    } else {
                        HashMap<String, Object> map = res.get(assetGeoStat.getStationName());
                        List statu_statistic = (List)  map.get("statu_statistic");
                        statu_statistic.add(new Integer[]{assetGeoStat.getStatu(),assetGeoStat.getCount()});
                    }
                }
                return ResponseResult.okResult(res);
            default:
                return null;
        }
    }
}
