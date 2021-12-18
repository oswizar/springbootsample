package com.oswizar.springbootsample.config.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的注解@TargetDataSource
 * 用于在service层使用该注解动态切换数据库
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TargetDataSource  {
    String value();
}
