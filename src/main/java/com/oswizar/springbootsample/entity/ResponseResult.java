package com.oswizar.springbootsample.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult implements Serializable {
    private int code;  //200为正常   400异常
    private String msg;
    private Object data;

    public static ResponseResult success(Object data){
        return result(200,"操作成功",data);
    }
    public static ResponseResult success(String msg, Object data){
        return result(200,msg,data);
    }
    public static ResponseResult fail(String msg){
        return result(400,msg,null);
    }
    public static ResponseResult fail(String msg, Object data){
        return result(400,msg,data);
    }

    public static ResponseResult result(int code, String msg, Object data){
        ResponseResult res = new ResponseResult();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

}
