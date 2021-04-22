package cn.peilion.log.controller;

import cn.peilion.log.apis.MaintenanceLogControllerApi;
import cn.peilion.log.service.MaintenanceRecordService;
import cn.peilion.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Log")
@RestController
@RequestMapping("/api/v1/maintenance")
public class MaintenanceLogController implements MaintenanceLogControllerApi {
    @Autowired
    private MaintenanceRecordService maintenanceRecordService;

    @Override
    @ApiOperation("维修记录统计")
    @GetMapping("/stat/")
    public ResponseResult getStatistic() {
        return maintenanceRecordService.loadStat();
    }
}
