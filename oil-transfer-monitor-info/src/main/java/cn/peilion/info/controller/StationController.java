package cn.peilion.info.controller;

import cn.peilion.info.apis.StationControllerApi;
import cn.peilion.info.service.StationService;
import cn.peilion.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Station")
@RequestMapping("/api/v1/station")
public class StationController implements StationControllerApi {

    @Autowired
    private StationService stationService;

    @Override
    @GetMapping("/tree")
    @ApiOperation("获取站场组织机构树形数据")
    public ResponseResult getStationTree() {
        return stationService.getStationTree();
    }

    @Override
    @GetMapping("/weather")
    @ApiOperation("获取站场天气数据列表")
    public ResponseResult getStationWeather() {
        return stationService.listStationWeather();
    }
}
