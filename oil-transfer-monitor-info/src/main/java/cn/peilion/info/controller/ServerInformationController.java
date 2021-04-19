package cn.peilion.info.controller;

import cn.peilion.info.apis.ServerInformationControllerApi;
import cn.peilion.info.service.ServerService;
import cn.peilion.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "Server")
@RequestMapping("/api/v1/server")
public class ServerInformationController implements ServerInformationControllerApi {

    @Autowired
    private ServerService serverService;

    @Override
    @GetMapping("/")
    @ApiOperation("获取服务器相关运行数据")
    public ResponseResult get() {
        return serverService.load();
    }
}
