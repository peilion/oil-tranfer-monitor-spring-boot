package cn.peilion.info.controller;

import cn.peilion.info.apis.MeasurePointControllerApi;
import cn.peilion.info.service.MeasurePointService;
import cn.peilion.model.common.dtos.MeasurePointListDto;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.enums.AppHttpCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fpl11
 */
@Api(tags = "Measure point")
@RestController
@RequestMapping("/api/v1/mp")
public class MeasurePointController implements MeasurePointControllerApi {

    private final List ruleConstraint = Arrays.asList(new String[]{"asset_id", "station_id", "statu"});

    @Autowired
    private MeasurePointService measurePointService;

    @Override
    @ApiOperation("获取测点信息列表")
    @GetMapping("/")
    public ResponseResult getList(MeasurePointListDto dto) {
        return measurePointService.loadList(dto);
    }

    @Override
    @ApiOperation("根据Id获取测点信息")
    @GetMapping("/{measurePointId}/")
    public ResponseResult getById(@PathVariable Integer measurePointId) {
        return measurePointService.loadById(measurePointId);
    }

    @Override
    @ApiOperation("获取测点信息统计")
    @GetMapping("/stat/")
    public ResponseResult getStat(String rule) {
        if (!ruleConstraint.contains(rule)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "分组规则不存在");
        }
        return measurePointService.loadStat(rule);
    }
}
