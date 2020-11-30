package com.xiexing.springbootdemo.controller;

import com.xiexing.springbootdemo.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloWorldController {

    @Value("${spring.dubbo.registry.address}")
    private String zkAddr;

    User user1 = new User();
    User user2 = new User();

    @ResponseBody
    @RequestMapping("/hello")
    public String index() {

        String hello = "Hello World";
//        List list = new ArrayList();
        user1.setUserId(Integer.parseInt("1"));
        user1.setUserName("小明1");
        user1.setPassWord("sdfjowhfowhfohf");

//        user2.setUserid(Integer.parseInt("2"));
//        user2.setUserName("小明2");
//        user2.setPassWord("sdfjowhfowhfohf");

//        list.add(user1);
//        list.add(user2);

        System.out.println(user1);
        System.out.println(zkAddr);
        return hello;
    }


//    @RequestMapping("/login")
//    @ResponseBody
//    public User login(User user) {
//
//
//        return user;
//    }

    @RequestMapping("/success")
//    @GetMapping("/success")
//    @PostMapping("/success")
    public String success(Map<String,Object> map) {
        map.put("obj","hello SpringBoot");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/helloString")
    public Object helloString() {
        Map<String,String> map = new HashMap<>();
        map.put("1","111");
        map.put("2","222");
        return map;
    }


}
