package cn.peilion.log.controller;

import cn.peilion.log.apis.ThresholdControllerApi;
import cn.peilion.log.service.ThresholdService;
import cn.peilion.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Threshold")
@RequestMapping("/api/v1/threshold")
@RestController
public class ThresholdController implements ThresholdControllerApi {

    @Autowired
    private ThresholdService thresholdService;

    @Override
    @ApiOperation("阈值列表")
    @GetMapping("/")
    public ResponseResult getThresholdList() {

        return thresholdService.loadList();
    }
}
