package com.oswizar.springbootsample.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.oswizar.springbootsample.model.OOM;
import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.entity.User;
import com.oswizar.springbootsample.service.TestIService;
import com.oswizar.springbootsample.util.RedisUtils;
import com.oswizar.springbootsample.util.ZxingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestIService testService;

    @GetMapping("/test")
    public ResponseResult test() {
        return ResponseResult.fail(500, "测试接口");
    }

    @GetMapping("/testRedisList")
    public ResponseResult testRedisList() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "0000");
        result.put("message", "请求成功");
        result.put("data", "1111111111111111111111111111111");
        RedisUtils.llSetSingle("OnlineUserStatistics","user1");
        RedisUtils.llSetSingle("OnlineUserStatistics","user2");
        RedisUtils.lRemove("OnlineUserStatistics", 1, "user1");

        return ResponseResult.success(JSON.toJSONString(result));
    }



    @GetMapping("/testRedis/{key}")
    public ResponseResult test(@PathVariable String key) {
        Map<String, Object> result = new HashMap<>();
        Object value = RedisUtils.get(key);
        result.put("code", "0000");
        result.put("message", "请求成功");
        result.put("data", value);
        return ResponseResult.success(JSON.toJSONString(result));
    }

    @GetMapping("/redisTest")
    public ResponseResult redisTest() {
        String str = "redis test";
        RedisUtils.set("example", str);
        log.info("redisssssssssssssssssssssssssssssssssssssssssssssss");
        return ResponseResult.success("redis test success", null);
    }

    @GetMapping("/redisGetValues/{key}")
    public ResponseResult redisGetValues(@PathVariable("key") String key) {
    
        log.info("请求参数 key:{}", key);
        Object o = RedisUtils.get(key);
        log.info("根据 key:{},获取到 values:{}", key, o);
    
        return ResponseResult.success(o);
    }

    @GetMapping("/redisHgetValues/{key}")
    public ResponseResult redisHgetValues(@PathVariable("key") String key) {
    
        log.info("请求参数 key:{}", key);
        Object o = RedisUtils.hmget(key);
        log.info("根据 key:{},获取到 values:{}", key, o);
    
        return ResponseResult.success(o);
    }

    @GetMapping("/redisDelete/{key}")
    public ResponseResult redisDelete(@PathVariable("key") String key) {
    
        log.info("请求参数 key:{}", key);
        RedisUtils.del(key);
        log.info("根据 key:{},删除数据成功", key);
    
        return ResponseResult.success("删除成功", null);
    }

    @GetMapping("/hmset")
    public ResponseResult setValues() {
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

        return ResponseResult.success("success", null);
    }


    @GetMapping("/accessControl")
//    @AccessLimit(limit = 5,sec = 60)  //加上自定义注解即可
    public ResponseResult getOrder(HttpServletRequest request) throws InterruptedException {
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
        System.out.println(request.getRequestURI());
        System.out.println("-----进入 getOrder 方法------");
        return ResponseResult.success("111111111111111", null);
    }



    @PostMapping("/operationLog")
    public ResponseResult operationLog() throws Exception {
        Map<String, Object> params = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("1234567890");
        params.put("taskId", "123");
        params.put("userId", "2911");
        Object result = testService.operationLog(params, list);
        return ResponseResult.success(result);
    }

    @GetMapping("/testOOM")
    public ResponseResult testOOM() {
        List<OOM> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new OOM());
        }
        return ResponseResult.success("end", null);
    }


    @GetMapping("/getId")
    public ResponseResult getId() {
        long id = IdUtil.getSnowflake(1L, 1L).nextId();
        System.out.println(id);
        return ResponseResult.success("end", null);
    }



}
