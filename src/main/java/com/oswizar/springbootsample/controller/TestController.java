package com.oswizar.springbootsample.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.oswizar.springbootsample.model.OOM;
import com.oswizar.springbootsample.entity.User;
import com.oswizar.springbootsample.service.TestIService;
import com.oswizar.springbootsample.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestIService testService;

    @GetMapping("/test")
    public Object test() {
        return null;
    }

    @RequestMapping("/testRedisList")
    public String testRedisList() {
        Map result = new HashMap();
        result.put("code", "0000");
        result.put("message", "请求成功");
        result.put("data", "1111111111111111111111111111111");
        RedisUtils.llSetSingle("OnlineUserStatistics","user1");
        RedisUtils.llSetSingle("OnlineUserStatistics","user2");
        RedisUtils.lRemove("OnlineUserStatistics", 1, "user1");

        return JSON.toJSONString(result);
    }



    @RequestMapping("/testRedis/{key}")
    public String test(@PathVariable String key) {
        Map result = new HashMap();
        Object value = RedisUtils.get(key);
        result.put("code", "0000");
        result.put("message", "请求成功");
        result.put("data", value);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/redisTest")
    public String redisTest() {
        String str = "redis test";
        RedisUtils.set("example", str);
        log.info("redisssssssssssssssssssssssssssssssssssssssssssssss");
        return "redis test success";
    }

    @RequestMapping("/redisGetValues/{key}")
    public Object redisGetValues(@PathVariable("key") String key) {

        log.info("请求参数key:{}", key);
        Object o = RedisUtils.get(key);
        log.info("根据key:{},获取到values:{}", key, o);

        return o;
    }

    @RequestMapping("/redisHgetValues/{key}")
    public Object redisHgetValues(@PathVariable("key") String key) {

        log.info("请求参数key:{}", key);
        Object o = RedisUtils.hmget(key);
        log.info("根据key:{},获取到values:{}", key, o);

        return o;
    }

    @RequestMapping("/redisDelete/{key}")
    public Object redisDelete(@PathVariable("key") String key) {

        log.info("请求参数key:{}", key);
        RedisUtils.del(key);
        log.info("根据key:{},删除数据成功", key);

        return "Delete Success";
    }

    @RequestMapping("/hmset")
    public Object setValues() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "tom");
        map.put("password", "tomcat");
        User user = new User();
        user.setId(1);
        user.setUsername("tom");
        user.setPassword("tomcat");

        RedisUtils.hmset("admin", map);
        RedisUtils.hset("userObject", "user", user);

        return "success";
    }


    @GetMapping("/accessControl")
//    @AccessLimit(limit = 5,sec = 60)  //加上自定义注解即可
    public Object getOrder(HttpServletRequest request) throws InterruptedException {
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
        System.out.println(request.getRequestURI());
        System.out.println("-----进入getOrder方法------");
        return "111111111111111";
    }



    @PostMapping("/operationLog")
    public Object operationLog() throws Exception {
        Map<String, Object> params = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("1234567890");
        params.put("taskId", "123");
        params.put("userId", "2911");
        Map<String, Object> stringObjectMap = testService.operationLog(params, list);
        return stringObjectMap;
    }

    @GetMapping("/testOOM")
    public Object testOOM() {
        List<OOM> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new OOM());
        }
        return "end";
    }


    @GetMapping("/getId")
    public Object getId() {
        long id = IdUtil.getSnowflake(1L, 1L).nextId();
        System.out.println(id);
        return "end";
    }



}
