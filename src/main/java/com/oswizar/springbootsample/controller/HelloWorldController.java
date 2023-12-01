package com.oswizar.springbootsample.controller;

import com.oswizar.springbootsample.util.ZxingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class HelloWorldController {

    @RequestMapping("/index")
    public String index() {
        return "Hello World";
    }

    @PreAuthorize("hasAuthority({'admin'})")
    @RequestMapping("/success")
    public String success(Map<String,Object> map) {
        map.put("obj","hello SpringBoot");
        return "success";
    }

    @RequestMapping("/helloString")
    public Object helloString() {
        Map<String,String> map = new HashMap<>();
        map.put("1","111");
        map.put("2","222");
        return map;
    }

    @RequestMapping("/generateQRCode")
    public Object generateQRCode(HttpServletRequest request) {
        String contents = request.getParameter("contents");
        Map<String, Object> res = new HashMap<>();
        try{
            File qrCodeImage = ZxingUtils.getQRCodeImage(contents, 128, "/Users/oswizar/Temp/test.jpeg");
            System.out.println(qrCodeImage);
            res.put("msg", "生成二维码成功");
        } catch (Exception e) {
            res.put("msg", "生成二维码失败");
            res.put("desc", e.getMessage());
            e.printStackTrace();
        }
        return res;
    }
}
