package com.oswizar.springbootsample.config.handler;

import com.alibaba.fastjson.JSON;
import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ResponseResult result = ResponseResult.fail(HttpStatus.UNAUTHORIZED.value(), "Security认证失败请重新登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
