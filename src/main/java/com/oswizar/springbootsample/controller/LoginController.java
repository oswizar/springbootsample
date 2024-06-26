package com.oswizar.springbootsample.controller;

import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.entity.User;
import com.oswizar.springbootsample.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/accessDenied")
    public Object accessDenied() {
        return "accessDenied";
    }


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user, HttpServletRequest request) {
        return loginService.login(user);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

}
