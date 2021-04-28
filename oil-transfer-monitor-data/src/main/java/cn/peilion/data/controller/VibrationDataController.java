package cn.peilion.data.controller;

import cn.peilion.data.apis.VibrationDataControllerApi;
import cn.peilion.data.service.VibrationDataService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.dtos.DataDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Data")
@RestController
@RequestMapping("/api/v1/mp")
public class VibrationDataController implements VibrationDataControllerApi {

    @Autowired
    VibrationDataService vibrationDataService;

    @Override
    @ApiOperation("根据测点id获取某条或最新的振动数据")
    @GetMapping("/{measurePointId}/vib/data")
    public ResponseResult getById(DataDto dto, @PathVariable Integer measurePointId) {
        return vibrationDataService.getById(dto, measurePointId);
    }
}
