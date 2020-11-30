package com.xiexing.springbootdemo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
public class User implements Serializable {


    private static final long serialVersionUID = 2890742645041401061L;
    private Integer userId;
    private String userName;
    private String passWord;

}