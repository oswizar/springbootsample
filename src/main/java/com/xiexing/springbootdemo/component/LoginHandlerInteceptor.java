/**
 * Copyright (C): 长安新生(深圳)金融投资有限公司
 * FileName: LoginHandlerInteceptor
 * Author:   xiexing
 * Date:     2019/3/13 15:49
 * Description:
 */
package com.xiexing.springbootdemo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截检查
 */
@Slf4j
public class LoginHandlerInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        log.info("从session中获取到的数据为{}",user);
        if (user == null) {
            // 登录检查未通过
            request.setAttribute("msg","没有权限请先登录");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;

        } else {
            // 登录检查通过
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
