package com.oswizar.springbootsample.config.handler;

import com.oswizar.springbootsample.entity.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult serverError() {
        return ResponseResult.result(500,"系统错误",null);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseResult accessDenied() {
        return ResponseResult.result(403,"无权访问",null);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseResult loginFail() {
        return ResponseResult.result(200,"用户名或密码错误",null);
    }
}
