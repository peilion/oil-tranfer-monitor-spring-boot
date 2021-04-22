package cn.peilion.log.controller;

import cn.peilion.log.apis.WarningLogControllerApi;
import cn.peilion.log.service.WarningLogService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.enums.AppHttpCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@Api(tags = "Log")
@RestController
@RequestMapping("/api/v1/warning")
public class WarningLogController implements WarningLogControllerApi {

    @Autowired
    private WarningLogService warningLogService;
    private final HashSet<String> ruleConstriant = new HashSet<String>() {{
        add("period");
        add("isread");
    }};

    @Override
    @GetMapping("/list/")
    @ApiOperation("报警记录列表")
    public ResponseResult getWarningList(Boolean brief) {
        return warningLogService.listWarningLog(brief);
    }

    @Override
    @GetMapping("/stat/")
    @ApiOperation("报警数量统计，按period或isread")
    public ResponseResult getStatistic(String rule) {
        if (!ruleConstriant.contains(rule)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return warningLogService.loadStatisticReport(rule);
    }

    @Override
    @GetMapping("/{id}/")
    @ApiOperation("报警详情")
    public ResponseResult getWarningLogById(@PathVariable("id") Integer id) {
        return warningLogService.loadById(id);
    }


    @Override
    @GetMapping("/asset/{id}/")
    @ApiOperation("设备近期事件")
    public ResponseResult getAssetRecentEvent(Integer assetId) {
        return warningLogService.listAssetEvent(assetId);
    }
}
