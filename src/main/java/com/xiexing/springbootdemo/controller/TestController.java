package com.xiexing.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.xiexing.springbootdemo.entity.AppAdvice;
import com.xiexing.springbootdemo.entity.Department;
import com.xiexing.springbootdemo.entity.Employee;
import com.xiexing.springbootdemo.entity.User;
import com.xiexing.springbootdemo.service.TestIService;
import com.xiexing.springbootdemo.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
@Api(description = "测试控制器")
public class TestController {

    public static void main(String[] args) {
        System.out.println();
    }

    @Autowired
    private TestIService testService;

    @Autowired
    private RedisUtils redisUtils;


    @ApiOperation(value="创建图书", notes="图书相关信息")
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
        result.put("code","0000");
        result.put("message","请求成功");
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
        instance.downLoadFile("/upload/example/","redis.conf","d:\\sftp");
    }


    @Test
    public void md5Test() {

        String md5String = Md5Utils.getMd5String("3857662@#%%9Od02Jd?//```d1");
        System.out.println(md5String);
        Integer[] sum = new Integer[]{1,2,3};

        for(int i:sum){
            System.out.println(i);

        }

        List list = Arrays.asList(sum);
        list.forEach(o -> System.out.println(o));

    }


}
