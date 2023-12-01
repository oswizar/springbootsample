package com.oswizar.springbootsample.config.handler;

import com.alibaba.fastjson.JSON;
import com.oswizar.springbootsample.entity.ResponseResult;
import com.oswizar.springbootsample.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ResponseResult result = ResponseResult.fail(String.valueOf(HttpStatus.FORBIDDEN.value()), "权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
