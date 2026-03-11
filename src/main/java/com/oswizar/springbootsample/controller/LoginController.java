package com.oswizar.springbootsample.controller;

import com.oswizar.springbootsample.entity.User;
import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/accessDenied")
    public ResponseResult accessDenied() {
        return ResponseResult.fail(403, "无权访问");
    }


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user, HttpServletRequest request) {
        Object login = loginService.login(user);
        return ResponseResult.success(login);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        loginService.logout();
        return ResponseResult.success("注销成功", null);
    }

}
