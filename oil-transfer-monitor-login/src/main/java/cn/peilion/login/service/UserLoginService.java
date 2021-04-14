package cn.peilion.login.service;


import cn.peilion.model.common.dtos.ResponseResult;

public interface UserLoginService {
    ResponseResult login();

    ResponseResult logout();
}
