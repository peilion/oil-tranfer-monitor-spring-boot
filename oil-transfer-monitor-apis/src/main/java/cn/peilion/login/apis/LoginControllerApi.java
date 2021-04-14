package cn.peilion.login.apis;

import cn.peilion.model.common.dtos.ResponseResult;

public interface LoginControllerApi {
    ResponseResult login();

    ResponseResult loginGet();

    ResponseResult info();

    ResponseResult logout();
}