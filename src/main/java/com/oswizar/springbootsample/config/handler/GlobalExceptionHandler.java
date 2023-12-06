package com.oswizar.springbootsample.config.handler;

import com.oswizar.springbootsample.model.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult serverError() {
        return ResponseResult.result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "System系统错误", null);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseResult accessDenied() {
        return ResponseResult.result(HttpStatus.FORBIDDEN.value(), "System无权访问", null);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseResult loginFail() {
        return ResponseResult.result(HttpStatus.UNAUTHORIZED.value(), "System用户名或密码错误", null);
    }
}
