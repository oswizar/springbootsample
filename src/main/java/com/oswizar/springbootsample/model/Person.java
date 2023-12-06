package com.oswizar.springbootsample.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 4603642343377807741L;
    private int age;
    private String name;
    private String sex;
}
