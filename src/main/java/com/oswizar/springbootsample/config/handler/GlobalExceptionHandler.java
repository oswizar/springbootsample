package com.oswizar.springbootsample.config.handler;

import com.oswizar.springbootsample.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult serverError(Exception e) {
        log.error(String.valueOf(e));
        return ResponseResult.result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "System服务器错误", null);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseResult accessDenied(Exception e) {
        log.error(String.valueOf(e));
        return ResponseResult.result(HttpStatus.FORBIDDEN.value(), "System无权访问", null);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseResult loginFail(Exception e) {
        log.error(String.valueOf(e));
        return ResponseResult.result(HttpStatus.UNAUTHORIZED.value(), "System用户名或密码错误", null);
    }

    /**
     * 适用于方法上使用@Validated @RequestBody
     */
    @ExceptionHandler({BindException.class})
    public ResponseResult handleMethodArgumentNotValidException(BindException e) {
        log.error(String.valueOf(e));
        BindingResult bindingResult = e.getBindingResult();
        List<String> errors = bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).collect(Collectors.toList());
        return ResponseResult.fail(HttpStatus.BAD_REQUEST.value(), errors.toString());
    }

    /**
     * 适用于类上@Validated，参数分别使用@NotNull @Min等
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseResult handleConstraintViolationException(ConstraintViolationException e) {
        log.error(String.valueOf(e));
        return ResponseResult.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
