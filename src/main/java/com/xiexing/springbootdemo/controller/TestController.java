package com.xiexing.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiexing.springbootdemo.entity.Department;
import com.xiexing.springbootdemo.entity.User;
import com.xiexing.springbootdemo.service.TestIService;
import com.xiexing.springbootdemo.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @date: 2019/4/3 17:14
 * @author: oswizar
 * @description:
 */
@Controller
@Slf4j
@Api(description = "测试控制器")
public class TestController {

//    public static void main(String[] args) {
//        System.out.println();
//    }

    @Autowired
    private TestIService testService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtils redisUtils;
//
//    @Autowired
//    public void setRestTemplate(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }


    @ApiOperation(value = "创建图书", notes = "图书相关信息")
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

    @ApiOperation(value = "获取图书详细信息", notes = "根据url的id来获取详细信息")
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
        redisUtils.set("example", str);
        log.info("redisssssssssssssssssssssssssssssssssssssssssssssss");
        return "redis test success";
    }

    @ResponseBody
    @RequestMapping("/redisGetValues/{key}")
    public Object redisGetValues(@PathVariable("key") String key) {

        log.info("请求参数key:{}", key);
        Object o = redisUtils.get(key);
        log.info("根据key:{},获取到values:{}", key, o);

        return o;
    }

    @ResponseBody
    @RequestMapping("/redisHgetValues/{key}")
    public Object redisHgetValues(@PathVariable("key") String key) {

        log.info("请求参数key:{}", key);
        Object o = redisUtils.hmget(key);
        log.info("根据key:{},获取到values:{}", key, o);

        return o;
    }

    @ResponseBody
    @RequestMapping("/redisDelete/{key}")
    public Object redisDelete(@PathVariable("key") String key) {

        log.info("请求参数key:{}", key);
        redisUtils.del(key);
        log.info("根据key:{},删除数据成功", key);

        return "Delete Success";
    }

    @RequestMapping("/hmset")
    @ResponseBody
    public Object setValues() {
        System.out.println(redisUtils.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "tom");
        map.put("password", "tomcat");
        User user = new User();
        user.setUserId(1);
        user.setUserName("tom");
        user.setPassWord("tomcat");

        redisUtils.hmset("admin", map);
        redisUtils.hset("userObject", "user",user);
        redisUtils.hmset("slave", JacksonUtils.jsonToMap(JsonUtils.obj2Json(user)));

        return "success";
    }


    @Test
    public void sendTest() {
//        System.out.println(HttpUtils.senPostParmaStr("http://www.baidu.com",""));
        System.out.println(HttpUtils.senPost("http://www.baidu.com", ""));
        System.out.println("=========================================================================");
        System.out.println(HttpUtils.sendPost("http://www.baidu.com", ""));


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

    @Test
    public void sftpTest() throws Exception {

        SFTPUtils instance = SFTPUtils.getInstance("10.10.10.136", 2222, "abc", "abc", "", "");
        System.out.println(instance);
//        instance.uploadFile("/up", "D:\\", "redis.conf");
//        instance.uploadFile("/upload/", "/example/", "D:\\","CACHE.log|redis.conf");

//        System.out.println("helloworld".substring(2,5));
        instance.downLoadFile("/upload/example/", "redis.conf", "d:\\sftp");
    }


    @Test
    public void md5Test() {

        String md5String = Md5Utils.getMd5String("3857662@#%%9Od02Jd?//```d1");
        System.out.println(md5String);
        Integer[] sum = new Integer[]{1, 2, 3};

        for (int i : sum) {
            System.out.println(i);

        }

        List list = Arrays.asList(sum);
        list.forEach(o -> System.out.println(o));

    }


    @ResponseBody
    @GetMapping("/accessControl")
//    @AccessLimit(limit = 5,sec = 60)  //加上自定义注解即可
    public Object getOrder(HttpServletRequest request) throws InterruptedException {
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
        System.out.println(request.getRequestURI());
        System.out.println(request.getRealPath("/"));
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
//        Object result = restTemplate.getForObject(url,Object.class,variable);
        Object result = HttpUtils.sendGet(url,variable);
        System.out.println(result);
//        Object result = HttpUtils.sendGet(url,variable);
        JSONObject jsonObject = JSONObject.parseObject((String) result);
        System.out.println(jsonObject);
        return result;

    }


}
