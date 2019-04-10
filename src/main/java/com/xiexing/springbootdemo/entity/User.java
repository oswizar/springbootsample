/**
 * Copyright (C): 长安新生(深圳)金融投资有限公司
 * FileName: User
 * Author:   xiexing
 * Date:     2018/12/20 15:26
 * Description: 用户实体
 */
package com.xiexing.springbootdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Setter
@Getter
@ToString
@ConfigurationProperties(prefix = "user")
@Component
public class User implements Serializable {


    private static final long serialVersionUID = 2890742645041401061L;
    private Integer userId;
    private String userName;
    private String passWord;

}