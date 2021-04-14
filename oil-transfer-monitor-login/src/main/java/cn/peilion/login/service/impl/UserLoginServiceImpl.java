package cn.peilion.login.service.impl;

import cn.peilion.common.constans.LoginConstant;
import cn.peilion.login.service.UserLoginService;
import cn.peilion.model.common.dtos.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Override
    public ResponseResult login() {
        HashMap<String, LoginConstant> map = new HashMap<>();
        LoginConstant loginConstant = new LoginConstant();
        map.put("admin", loginConstant);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "success");
        return ResponseResult.okResult(map);
    }
}
