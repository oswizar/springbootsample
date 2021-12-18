package com.oswizar.springbootsample.controller;

import com.oswizar.springbootsample.entity.User;
import com.oswizar.springbootsample.util.ZxingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class HelloWorldController {

    @Value("${spring.dubbo.registry.address}")
    private String zkAddr;

    User user1 = new User();
    User user2 = new User();

    @ResponseBody
    @RequestMapping("/hello")
    public String index(@RequestBody Map dm) {

        System.out.println(dm);

        String hello = "Hello World";
//        List list = new ArrayList();
        user1.setUserId(Integer.parseInt("1"));
        user1.setUserName("小明1");
        user1.setPassword("sdfjowhfowhfohf");

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


    @ResponseBody
    @RequestMapping("/generateQRCode")
    public Object generateQRCode(HttpServletRequest request) {
        String url = request.getParameter("url");
        System.out.println(url);
        try{
            File qrCodeImge = ZxingUtils.getQRCodeImge(url, 128, "D:/test/test.jpeg");
            System.out.println(qrCodeImge);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("生成推荐二维码失败");
        }
        return "生成成功";
    }


}
