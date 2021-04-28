package cn.peilion.log.service.impl;

import cn.peilion.log.service.WarningLogService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.enums.AppHttpCodeEnum;
import cn.peilion.model.mappers.VibrationDataMapper;
import cn.peilion.model.mappers.WarningLogMapper;
import cn.peilion.model.pojos.configuration.MaintenanceSuggestion;
import cn.peilion.model.pojos.log.WarningLog;
import cn.peilion.model.pojos.log.WarningLogTableItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class WarningLogServiceImpl implements WarningLogService {

    @Autowired
    private WarningLogMapper warningLogMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VibrationDataMapper vibrationDataMapper;


    @Override
    public ResponseResult loadStatisticReport(String rule) {
        if ("period".equals(rule)) {
            Date now = new Date();
            Integer[] daysDeltas = {1, 7, 30, 365};
            HashMap<Integer, Integer> result = new HashMap<>(daysDeltas.length);
            Calendar calendar = Calendar.getInstance();
            for (Integer daysDelta : daysDeltas) {
                calendar.setTime(now);
                calendar.add(Calendar.DATE, -daysDelta);
                Date after = calendar.getTime();
                Integer warningCount = warningLogMapper.loadStatisticByPeriod(after, now);
                result.put(daysDelta, warningCount);
            }
            return ResponseResult.okResult(result);
        } else if ("isread".equals(rule)) {
            return ResponseResult.okResult(warningLogMapper.loadStatisticByReadBehavior());
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
    }

    @Override
    public ResponseResult loadById(Integer id) {
        WarningLog warningLog = warningLogMapper.loadById(id);
        HashMap faultPatterns = warningLog.getDescription();
        List<MaintenanceSuggestion> maintenanceSuggestions = warningLogMapper.loadSuggestions(faultPatterns.entrySet());
        HashMap<String, Object> res = new HashMap<>(2);
        res.put("suggestions", maintenanceSuggestions);
        res.put("detail", warningLog);
        return ResponseResult.okResult(res);
    }


    @Override
    public ResponseResult listWarningLog(Boolean brief) {
        if (!brief) {
            List<WarningLogTableItem> warningLogList = warningLogMapper.loadList(200);
            return ResponseResult.okResult(warningLogList);
        } else if (brief) {
            List<WarningLogTableItem> warningLogList = warningLogMapper.loadBriefList();
            return ResponseResult.okResult(warningLogList);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
    }

    @Override
    public ResponseResult listAssetEvent(Integer assetId) {
        return ResponseResult.okResult(warningLogMapper.loadRecentAssetEvent(assetId));
    }
}
