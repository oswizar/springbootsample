package com.oswizar.springbootsample.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Order(5)
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义切点
     */
    @Pointcut("execution(* com.oswizar.springbootsample.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 方法执行前
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL>>>>>>>>>>>>" + request.getRequestURL().toString());
        logger.info("HTTP_METHOD>>>>" + request.getMethod());
        logger.info("IP>>>>>>>>>>>>>" + request.getRemoteAddr());
        logger.info("CLASS_METHOD>>>" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS>>>>>>>>>>>" + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 方法执行后
     */
    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) {
        logger.info("RESPONSE>>>>>>>" + result + "\n");
    }

}
