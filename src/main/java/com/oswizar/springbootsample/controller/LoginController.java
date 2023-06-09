/**
 * Copyright (C): 长安新生(深圳)金融投资有限公司
 * FileName: LoginController
 * Author:   oswizar
 * Date:     2019/3/13 14:41
 * Description:
 */
package com.oswizar.springbootsample.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
public class LoginController {


    /**
     * 员工登录
     * @param username
     * @param password
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session) {
        log.info("进入登录控制器=====================================");
        if(!StringUtils.isEmpty(username) && "123456".equals(password)) {
            // 登录成功,将登录信息存入session;防止表单重复提交,重定向到主页
            session.setAttribute("loginUser",username);
            log.info("登录成功================================");
            return "redirect:/main.html";
        } else {
            // 登录失败返回登录页
            log.info("登录失败================================");
            map.put("msg","用户名/密码错误");
            return "login";
        }
    }


    /**
     * 员工登出
     * @param request
     * @return
     */
    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        // 获取保存在请求域中session
        String sessionId = request.getSession().getId();
        Object sessionInfo = request.getSession().getAttribute("loginUser");
        log.info("清除session里数据前的sessionId:{};保存在session里的数据:{}",sessionId,sessionInfo);
        // 清除session里的数据
        request.getSession().invalidate();
        sessionId=request.getSession().getId();
        sessionInfo = request.getSession().getAttribute("loginUser");
        log.info("清除session里数据后的sessionId:{};保存在session里的数据:{}",sessionId,sessionInfo);
        log.info("========================登出系统==========================");
        // 登出之后重定向登录页面
        return "redirect:/index.html";
    }

}
