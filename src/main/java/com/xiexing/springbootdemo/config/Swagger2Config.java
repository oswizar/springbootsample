package com.xiexing.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @date: 2019/6/10 17:26
 * @author: oswizar
 * @description: 配置swagger
 */
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiexing.springbootdemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //描述
                .description("swagger接入教程")
                //页面标题
                .title("SpringBoot 使用 Swagger2 构建RESTfulAPI")
                //服务条款
                .termsOfServiceUrl("http://blog.csdn.net/")
                //联系方式
                .contact(new Contact("oswizar", "http://blog.bianxh.top/", "oswizar@163.com"))
                //版本号
                .version("1.0")
                .build();
    }
}
