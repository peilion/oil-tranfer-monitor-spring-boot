package cn.peilion.log.service.impl;

import cn.peilion.log.service.MaintenanceRecordService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.mappers.MaintenanceRecordMapper;
import cn.peilion.model.pojos.log.MaintenanceRecordStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceRecordServiceImpl implements MaintenanceRecordService {

    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;

    @Override
    public ResponseResult loadStat() {
        List<MaintenanceRecordStat> statisticResult = maintenanceRecordMapper.loadStatByStatu();
        return ResponseResult.okResult(statisticResult);
    }
}
