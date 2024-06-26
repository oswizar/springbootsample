package com.oswizar.springbootsample.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.oswizar.springbootsample.config.ConfigConstants;
import com.oswizar.springbootsample.entity.User;
import com.oswizar.springbootsample.model.LoginUser;
import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.service.LoginService;
import com.oswizar.springbootsample.util.RedisUtils;
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
        Map<String, Object> payload = new HashMap<>();
        DateTime now = DateTime.now();
        DateTime exp = now.offsetNew(DateField.SECOND, 60 * 60 * 12);

        // reserved claims
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, exp);

        // private claims
        payload.put("username", loginUser.getUsername());

        String jwt = JWTUtil.createToken(payload, ConfigConstants.JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        Map<String, String> tokenMap = Map.of("token", jwt);
        RedisUtils.set("login:" + loginUser.getUsername(), loginUser, 60 * 60 * 12);
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
