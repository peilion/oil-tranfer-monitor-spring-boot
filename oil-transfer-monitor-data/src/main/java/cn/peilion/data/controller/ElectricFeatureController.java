package cn.peilion.data.controller;

import cn.peilion.data.apis.ElectricFeatureControllerApi;
import cn.peilion.data.service.ElectricFeatureService;
import cn.peilion.model.common.dtos.ElectricFeatureDto;
import cn.peilion.model.common.dtos.ElectricFeatureListDto;
import cn.peilion.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Data")
@RestController
@RequestMapping("/api/v1/mp")
public class ElectricFeatureController implements ElectricFeatureControllerApi {

    @Autowired
    ElectricFeatureService electricFeatureService;

    @Override
    @ApiOperation("根据测点id获取某条或最新的电流特征")
    @GetMapping("/{measurePointId}/elec/feature")
    public ResponseResult getById(@Validated ElectricFeatureDto dto, @PathVariable Integer measurePointId) {
        return electricFeatureService.getById(dto,measurePointId);
    }

    @Override
    @ApiOperation("根据测点id及时间范围获取电流特征序列")
    @GetMapping("/{measurePointId}/elec/feature/list")
    public ResponseResult getList(ElectricFeatureListDto dto, Integer measurePointId) throws NoSuchFieldException, IllegalAccessException {
        return electricFeatureService.getList(dto,measurePointId);
    }
}
