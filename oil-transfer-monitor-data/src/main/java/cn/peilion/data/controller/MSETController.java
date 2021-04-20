package cn.peilion.data.controller;

import cn.peilion.data.apis.MSETControllerApi;
import cn.peilion.data.service.MSETService;
import cn.peilion.model.common.dtos.MSETDto;
import cn.peilion.model.common.dtos.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mset")
@Api(tags = "MSET")
public class MSETController implements MSETControllerApi {

    @Autowired
    private MSETService msetService;

    @ApiOperation("获取相似度及动态阈值列表")
    @Override
    @GetMapping("/{id}/mset/")
    public ResponseResult getSimilarityAndThresholdList(MSETDto dto, @PathVariable("id") Integer id) {
        return msetService.loadSimilarityAndThresholdList(dto, id);
    }

    @ApiOperation("获取估计值列表")
    @Override
    @GetMapping("/{id}/est/")
    public ResponseResult getEstimationList(MSETDto dto, @PathVariable("id") Integer id) throws JsonProcessingException {
        return msetService.loadEstimation(dto, id);
    }

    @ApiOperation("根据数据id获取估计值")
    @Override
    @GetMapping("/{id}/est/{dataId}")
    public ResponseResult getEstimationById(@PathVariable("dataId") Integer dataId, @PathVariable("id") Integer id) {
        return msetService.loadEstimation(id, dataId);
    }
}
