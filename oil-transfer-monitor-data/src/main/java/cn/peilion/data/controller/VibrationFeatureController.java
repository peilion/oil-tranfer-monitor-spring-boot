package cn.peilion.data.controller;

import cn.peilion.data.apis.VibrationFeatureControllerApi;
import cn.peilion.data.service.VibrationFeatureService;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.model.common.dtos.VibrationFeatureDto;
import cn.peilion.model.common.dtos.VibrationFeatureListDto;
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
public class VibrationFeatureController implements VibrationFeatureControllerApi {

    @Autowired
    VibrationFeatureService vibrationFeatureService;

    @Override
    @ApiOperation("根据测点id获取某条或最新的振动特征")
    @GetMapping("/{measurePointId}/vib/feature")
    public ResponseResult get(@Validated VibrationFeatureDto dto, @PathVariable Integer measurePointId) {
        return vibrationFeatureService.getById(dto, measurePointId);
    }

    @Override
    @ApiOperation("根据测点id及时间范围获取振动特征序列")
    @GetMapping("/{measurePointId}/vib/feature/list")
    public ResponseResult getList(VibrationFeatureListDto dto, Integer measurePointId) throws NoSuchFieldException, IllegalAccessException {
        return vibrationFeatureService.getList(dto, measurePointId);
    }
}
