package cn.peilion.data.controller;

import cn.peilion.data.apis.ElectricDataControllerApi;
import cn.peilion.data.service.ElectricDataService;
import cn.peilion.model.common.dtos.DataDto;
import cn.peilion.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Api(tags = "Data")
@RestController
@RequestMapping("/api/v1/mp")
public class ElectricDataController implements ElectricDataControllerApi {

    @Autowired
    ElectricDataService electricDataService;

    @Override
    @ApiOperation("根据测点id获取某条或最新的电流数据")
    @GetMapping("/{measurePointId}/elec/data")
    public ResponseResult getById(DataDto dto, @PathVariable Integer measurePointId) throws ExecutionException, InterruptedException {
        return electricDataService.getById(dto, measurePointId);
    }
}
