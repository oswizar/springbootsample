package com.oswizar.springbootsample.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oswizar.springbootsample.entity.Department;
import com.oswizar.springbootsample.entity.OOM;
import com.oswizar.springbootsample.entity.User;
import com.oswizar.springbootsample.service.TestIService;
import com.oswizar.springbootsample.util.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @date: 2019/4/3 17:14
 * @author: oswizar
 * @description:
 */
@Controller
@Slf4j
public class TestController {

//    @Autowired
    private final RestTemplate restTemplate = new RestTemplate() ;

//    public static void main(String[] args) {
//        System.out.println();
//    }

    @Autowired
    private TestIService testService;

//
//    @Autowired
//    public void setRestTemplate(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @ResponseBody
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


//    @ApiImplicitParam(name = "book", value = "图书详细实体", required = true, dataType = "Book")
    @ResponseBody
    @RequestMapping("/testInterfaceMybatis")
    public Map testInterfaceMybatis() {
        Map result = new HashMap();
//        Map param = new HashMap();
//        param.put("instanceId", "2E767A073E2216F9");
        Map appAdvices = testService.queryWfiAppAdvice();
        log.info("查询数据库返回=================>{}", appAdvices);
        result.put("data", appAdvices);
        return result;
    }

//    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
    @ResponseBody
    @RequestMapping(value = "/testDataSource")
    public Map testDataSource() {
        Map result = new HashMap();
        Department department = testService.queryDepartment("1");
        log.info("查询数据库返回=================>{}", department.toString());
        result.put("data", department);
        return result;
    }


    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        Map result = new HashMap();
        result.put("code", "0000");
        result.put("message", "请求成功");
        result.put("data", "1111111111111111111111111111111");
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping("/redisTest")
    public String redisTest() {
        String str = "redis test";
        RedisUtils.set("example", str);
        log.info("redisssssssssssssssssssssssssssssssssssssssssssssss");
        return "redis test success";
    }

    @ResponseBody
    @RequestMapping("/redisGetValues/{key}")
    public Object redisGetValues(@PathVariable("key") String key) {

        log.info("请求参数key:{}", key);
        Object o = RedisUtils.get(key);
        log.info("根据key:{},获取到values:{}", key, o);

        return o;
    }

    @ResponseBody
    @RequestMapping("/redisHgetValues/{key}")
    public Object redisHgetValues(@PathVariable("key") String key) {

        log.info("请求参数key:{}", key);
        Object o = RedisUtils.hmget(key);
        log.info("根据key:{},获取到values:{}", key, o);

        return o;
    }

    @ResponseBody
    @RequestMapping("/redisDelete/{key}")
    public Object redisDelete(@PathVariable("key") String key) {

        log.info("请求参数key:{}", key);
        RedisUtils.del(key);
        log.info("根据key:{},删除数据成功", key);

        return "Delete Success";
    }

    @RequestMapping("/hmset")
    @ResponseBody
    public Object setValues() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "tom");
        map.put("password", "tomcat");
        User user = new User();
        user.setUserId(1);
        user.setUserName("tom");
        user.setPassword("tomcat");

        RedisUtils.hmset("admin", map);
        RedisUtils.hset("userObject", "user",user);

        return "success";
    }




    @ResponseBody
    @GetMapping("/accessControl")
//    @AccessLimit(limit = 5,sec = 60)  //加上自定义注解即可
    public Object getOrder(HttpServletRequest request) throws InterruptedException {
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
        System.out.println(request.getRequestURI());
        System.out.println("-----进入getOrder方法------");
        return "111111111111111";
    }


    @ResponseBody
    @RequestMapping("/testInput")
    public String testInput(String input) {
        log.info("接收前端传入的参数为：[{}]",input);

        String str = "（一）低门槛全覆盖：对企业成立时间无硬性要求，初创期企业也可以申请；\n" +
                "（二）信用贷免担保：免担保、无抵押，真正意义上的纯信用贷款；\n" +
                "（三）维度多额度高：通过打分模板对应贷款额度，创始人经历、家庭情况、企业情况、外部认证等多个维度都可以成为“加分项”；\n" +
                "（四）标准化手续快：手续齐全的前提下最快一周可以放款。";
        System.out.println("str:" + str);


        Map result = new HashMap();
        result.put("code", "0000");
        result.put("message", "请求成功");
        result.put("data", "1111111111111111111111111111111");
        return JSON.toJSONString(result);
    }



    @ResponseBody
    @RequestMapping("/getNextWorkDay")
    public Object getNextWorkDay(Map<String, Object> input) {
        log.info("接收前端传入的参数为：[{}]",input);
        String url = "http://tool.bitefu.net/jiari/";
        Map<String,String> variable = new HashMap<>();
        variable.put("d","20201118");
        variable.put("apikey","123456");
        variable.put("type","6");
        Object result = restTemplate.getForObject(url,Object.class,variable);
//        Object result = HttpUtils.sendGet(url,variable);
        System.out.println(result);
//        Object result = HttpUtils.sendGet(url,variable);
        JSONObject jsonObject = JSONObject.parseObject((String) result);
        System.out.println(jsonObject);
        return result;

    }

    @PostMapping("/match")
    @ResponseBody
    public Object match() {

        MultiValueMap<String, Object> postParameters  = new LinkedMultiValueMap<>();
        postParameters .add("user_id", 1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, headers);

        List<Integer> object = (List<Integer>) restTemplate.postForObject("http://10.114.10.144:8080/api/model/i_match",
                httpEntity, Object.class);

        System.out.println(object);
        object.add(111111111);
        object.add(222222222);
        object.add(null);
        object.add(null);

        return object;
    }

    @PostMapping("/operationLog")
    @ResponseBody
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
    @ResponseBody
    public Object testOOM() {
        List<OOM> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new OOM());
        }
        return "end";
    }



}
