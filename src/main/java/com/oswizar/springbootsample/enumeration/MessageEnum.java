package com.oswizar.springbootsample.enumeration;

public enum MessageEnum {

    /**
     * 为什么会自动对应国际化配置文件???
     */
    LOGIN_PASSWORD("LOGIN_PASSWORD", "login.password");

    private String code;
    private String msgKey;

    MessageEnum(String code, String msgKey) {
        this.code = code;
        this.msgKey = msgKey;
    }
}
