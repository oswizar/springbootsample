package com.oswizar.springbootsample.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.oswizar.springbootsample.entity.LoginUser;
import com.oswizar.springbootsample.entity.ResponseResult;
import com.oswizar.springbootsample.entity.User;
import com.oswizar.springbootsample.service.LoginService;
import com.oswizar.springbootsample.util.RedisUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String jwt = JWTUtil.createToken(Map.of("username", loginUser.getUsername()), "secret".getBytes(StandardCharsets.UTF_8));
        Map<String, String> tokenMap = Map.of("token", jwt);
        RedisUtils.set("login:" + loginUser.getUsername(), loginUser);
        return ResponseResult.success(tokenMap);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        String userKey = "login:" + principal.getUsername();
        RedisUtils.del(userKey);
        return ResponseResult.success(null);
    }
}
