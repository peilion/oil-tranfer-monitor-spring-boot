package cn.peilion.login.controller;

import cn.peilion.login.apis.LoginControllerApi;
import cn.peilion.model.common.dtos.ResponseResult;
import cn.peilion.login.service.UserLoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Login")
@RestController
@RequestMapping("/api/v1/user")
public class LoginController implements LoginControllerApi {

    @Autowired
    private UserLoginService userLoginService;


    @Override
    @ApiOperation("登录用户")
    @PostMapping("/login")
    public ResponseResult login() {
        return userLoginService.login();
    }

    @Override
    @ApiOperation("登录用户")
    @GetMapping("/login")
    public ResponseResult loginGet() {
        return userLoginService.login();
    }

    @Override
    @ApiOperation("用户信息")
    @GetMapping("/info")
    public ResponseResult info() {
        return userLoginService.login();
    }

    @Override
    @ApiOperation("登出用户")
    @PostMapping("/logout")
    public ResponseResult logout() {
        return userLoginService.logout();
    }
}
