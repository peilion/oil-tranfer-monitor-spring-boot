package cn.peilion.info.controller;

import cn.peilion.common.restTemplate.MyRestTemplate;
import cn.peilion.info.apis.AssetControllerApi;
import cn.peilion.info.service.AssetService;
import cn.peilion.model.common.dtos.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Api(tags = "Asset")
@RestController
@RequestMapping("/api/v1/asset")
public class AssetController implements AssetControllerApi {

    @Autowired
    AssetService assetService;
    @Autowired
    private MyRestTemplate myRestTemplate;

    @Value("${service-url.log-service}")
    private String logServiceUrl;

    @Override
    @ApiOperation("获取设备信息卡片列表")
    @GetMapping("/cards/")
    public ResponseResult getCards() {
        return assetService.loadCards();
    }

    @Override
    @ApiOperation("根据id获取设备信息卡片")
    @GetMapping("/card/{id}")
    public ResponseResult getCard(@PathVariable("id") Integer id) {
        return assetService.loadCard(id);
    }

    @Override
    @ApiOperation("根据id获取设备详情")
    @GetMapping("/{id}")
    public ResponseResult getDetail(@PathVariable("id") Integer id) {
        return assetService.loadDetail(id);
    }

    @Override
    @ApiOperation("根据分组规则获取设备统计信息，statu type health_indicator geo")
    @GetMapping("/stat/")
    public ResponseResult getStatistic(String rule) {
        return assetService.loadStat(rule);
    }

    @Override
    @ApiOperation("获取设备信息及日志记录")
    @GetMapping("/{id}/with_log/")
    public ResponseResult getCardWithLog(@PathVariable("id") Integer id) throws JsonProcessingException {

        ResponseResult logs = myRestTemplate.getForResult(logServiceUrl + "/api/v1/warning/asset/{id}/", id);
        ResponseResult asset = assetService.loadCard(id);

        HashMap<String, Object> res = new HashMap<>(2);
        res.put("log", logs.getData());
        res.put("asset", asset.getData());
        return ResponseResult.okResult(res);
    }
}
