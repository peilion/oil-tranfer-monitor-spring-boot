package cn.peilion.info.service.impl;

import cn.peilion.info.service.MeasurePointService;
import cn.peilion.model.common.dtos.MeasurePointListDto;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.enums.AppHttpCodeEnum;
import cn.peilion.model.mappers.MeasurePointMapper;
import cn.peilion.model.pojos.measurePoint.MeasurePoint;
import cn.peilion.model.pojos.measurePoint.MeasurePointRelated;
import cn.peilion.model.pojos.measurePoint.MeasurePointStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MeasurePointServiceImpl implements MeasurePointService {

    @Autowired
    private MeasurePointMapper measurePointMapper;

    @Override
    public ResponseResult loadById(Integer measurePointId) {
        MeasurePoint measurePoint = measurePointMapper.loadMeasurePoint(measurePointId);
        if (null != measurePoint) {
            return ResponseResult.okResult(measurePoint);
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "测点不存在");
        }
    }

    @Override
    public ResponseResult loadList(MeasurePointListDto dto) {
        List<MeasurePointRelated> measurePointList = measurePointMapper.loadMeasurePointList(dto);
        if (null != measurePointList) {
            return ResponseResult.okResult(measurePointList);
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "测点不存在");
        }
    }

    @Override
    public ResponseResult loadStat(String rule) {
        List<MeasurePointStat> measurePointStat = measurePointMapper.loadMeasurePointStat(rule);
        Map<Integer, Integer> res = measurePointStat.stream()
                .collect(Collectors.toMap(stat -> stat.getFiled(), stat -> stat.getCnt()));
        return ResponseResult.okResult(res);
    }
}
