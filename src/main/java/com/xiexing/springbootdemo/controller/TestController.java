package com.xiexing.springbootdemo.controller;

import com.xiexing.springbootdemo.entity.AppAdvice;
import com.xiexing.springbootdemo.entity.Department;
import com.xiexing.springbootdemo.entity.Employee;
import com.xiexing.springbootdemo.entity.User;
import com.xiexing.springbootdemo.service.TestIService;
import com.xiexing.springbootdemo.util.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @date: 2019/4/3 17:14
 * @author: oswizar
 * @description:
 */
@Controller
@Slf4j
public class TestController {

    @Autowired
    private TestIService testService;

    @Autowired
    private RedisUtils redisUtils;

    @ResponseBody
    @RequestMapping("/testInterfaceMybatis")
    public Map testInterfaceMybatis() {
        Map result = new HashMap();
        Map param = new HashMap();
        param.put("instanceId","2E767A073E2216F9");
        List<AppAdvice> appAdvices = new ArrayList<>();
        appAdvices = testService.queryWfiAppAdvice(param);
        log.info("查询数据库返回=================>{}",appAdvices.toString());
        result.put("data",appAdvices);
        return result;
    }

    @ResponseBody
    @RequestMapping("/testDataSource")
    public Map testDataSource() {
        Map result = new HashMap();
        Department department = new Department();
        department = testService.queryDepartment("1");
        log.info("查询数据库返回=================>{}",department.toString());
        result.put("data",department);
        return result;
    }


    @ResponseBody
    @RequestMapping("/test")
    public Map test() {
        Map result = new HashMap();
        result.put("data","1111111111111111111111111111111");
        return result;
    }

    @ResponseBody
    @RequestMapping("/redisTest")
    public String redisTest() {
        String str = "redis test";
        redisUtils.set("example",str);
        log.info("redisssssssssssssssssssssssssssssssssssssssssssssss");
        return "redis test success";
    }

    @Test
    public void sendTest() {
//        System.out.println(HttpUtils.senPostParmaStr("http://www.baidu.com",""));
        System.out.println(HttpUtils.senPost("http://www.baidu.com",""));
        System.out.println("=========================================================================");
        System.out.println(HttpUtils.sendPost("http://www.baidu.com",""));



    }

    @Test
    public void dateTest() {
        Date date = DateUtils.getCurrentDate();
        System.out.println(DateUtils.getCurrentFormatDate("yyyy-MM"));
        System.out.println(DateUtils.getMonthStr(date));

        System.out.println(DateUtils.getYear());

        User user = new User();
        user.setUserId(1);
        user.setUserName("tom");
        user.setPassWord("tomcat");


        String xml = XmlUtils.toXML(user);
        System.out.println(xml);
        System.out.println(XmlUtils.xmlToObject(xml, user.getClass()));

    }



}
