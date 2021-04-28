package cn.peilion.data.service.impl;

import cn.peilion.data.component.MeasurePointMapHolder;
import cn.peilion.data.service.VibrationDataService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.dtos.DataDto;
import cn.peilion.model.common.enums.AppHttpCodeEnum;
import cn.peilion.model.mappers.VibrationDataMapper;
import cn.peilion.model.pojos.measurePoint.MeasurePoint;
import cn.peilion.model.pojos.vibration.VibrationData;
import cn.peilion.utils.dsp.Detrend;
import cn.peilion.utils.dsp.FastFourierTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;


@Service
public class VibrationDataServiceImpl implements VibrationDataService {

    @Autowired
    private VibrationDataMapper vibrationDataMapper;
    @Autowired
    private MeasurePointMapHolder measurePointMapHolder;

    @Override
    public ResponseResult getById(DataDto dto, Integer measurePointId) {
        MeasurePoint measurePoint = measurePointMapHolder.getCachedMeasurePoint(measurePointId);
        if (measurePoint == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "测点不存在");
        }
        if (measurePoint.getType().equals(1)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "测点类型与接口不匹配");
        }
        VibrationData vibData = vibrationDataMapper.loadVibrationData(dto, measurePoint.getStationId(), measurePoint.getInnerStationId());

        Detrend.process(vibData.getIma());
        Map<String, float[]> processedResult = FastFourierTransform.process(vibData.getIma(), 10000);
        TreeMap<String, Object> res = new TreeMap<>();
        res.put("vibration_data", vibData);
        res.put("spectrum", processedResult);
        return ResponseResult.okResult(res);
    }
}
