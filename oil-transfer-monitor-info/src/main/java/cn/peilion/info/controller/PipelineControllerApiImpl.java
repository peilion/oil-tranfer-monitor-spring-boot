package cn.peilion.info.controller;

import cn.peilion.info.apis.PipelineControllerApi;
import cn.peilion.info.service.PipelineService;
import cn.peilion.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "pipeline")
@RestController
@RequestMapping("/api/v1/pipeline")
public class PipelineControllerApiImpl implements PipelineControllerApi {

    @Autowired
    private PipelineService pipelineService;

    @Override
    @GetMapping("/total")
    @ApiOperation("获取管道总长度")
    public ResponseResult getTotalLength() {
        return pipelineService.getTotal();
    }
}
