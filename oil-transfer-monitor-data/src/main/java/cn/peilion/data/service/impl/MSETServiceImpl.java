package cn.peilion.data.service.impl;

import cn.peilion.data.service.MSETService;
import cn.peilion.model.common.dtos.MSETDto;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.mappers.MSETMapper;
import cn.peilion.model.pojos.asset.AssetHi;
import cn.peilion.model.pojos.asset.MSETEstimation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MSETServiceImpl implements MSETService {

    @Autowired
    private MSETMapper msetMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseResult loadSimilarityAndThresholdList(MSETDto dto, Integer id) {
        List<AssetHi> assetHiList = msetMapper.loadSimilarityAndThreshold(id, dto);
        return ResponseResult.okResult(assetHiList);
    }

    @Override
    public ResponseResult loadEstimation(MSETDto dto, Integer id) throws JsonProcessingException {
        List<AssetHi> assetHiList = msetMapper.loadThresholdList(id, dto);
        HashMap<String, Object> res = new HashMap<>();
        for (AssetHi assetHi : assetHiList) {
            res.putIfAbsent("id", new ArrayList<Integer>());
            res.putIfAbsent("time", new ArrayList<Date>());
            ArrayList<Date> timeList = (ArrayList<Date>) res.get("time");
            timeList.add(assetHi.getTime());
            ArrayList<Long> idList = (ArrayList<Long>) res.get("id");
            idList.add(assetHi.getId());

            MSETEstimation msetEstimation = objectMapper.readValue(assetHi.getEst(), MSETEstimation.class);
            for (int i = 0; i < msetEstimation.getLabel().length; i++) {
                String filed = msetEstimation.getLabel()[i];
                res.putIfAbsent(filed + "-原始值", new ArrayList<Float>());
                res.putIfAbsent(filed + "-估计值", new ArrayList<Float>());

                ArrayList<Float> rawList = (ArrayList<Float>) res.get(filed + "-原始值");
                rawList.add(msetEstimation.getRaw()[i]);
                ArrayList<Float> estList = (ArrayList<Float>) res.get(filed + "-估计值");
                estList.add(msetEstimation.getEst()[i]);
            }
        }
        return ResponseResult.okResult(res);
    }

    @Override
    public ResponseResult loadEstimation(Integer id, Integer dataId) throws JsonProcessingException {
        AssetHi assetHi = msetMapper.loadThreshold(id, dataId);
        MSETEstimation msetEstimation = objectMapper.readValue(assetHi.getEst(), MSETEstimation.class);
        return ResponseResult.okResult(msetEstimation);
    }
}
