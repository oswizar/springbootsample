//package com.xiexing.springbootdemo.config;
//
//import org.apache.ibatis.session.Configuration;
//import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
//import org.springframework.context.annotation.Bean;
//
///**
// * @date: 2019/5/15 16:18
// * @author: oswizar
// * @description: 自定义MyBatis配置项
// */
//@org.springframework.context.annotation.Configuration
//public class MyBatisConfig {
//
//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return new ConfigurationCustomizer() {
//            @Override
//            public void customize(Configuration configuration) {
//                // 配置驼峰命名规则
//                configuration.setMapUnderscoreToCamelCase(true);
//            }
//        };
//    }
//
//}
