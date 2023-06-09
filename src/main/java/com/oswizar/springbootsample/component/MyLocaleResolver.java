/**
 * Copyright (C): 长安新生(深圳)金融投资有限公司
 * FileName: MyLocaleResolver
 * Author:   oswizar
 * Date:     2019/3/7 17:12
 * Description:
 */
package com.oswizar.springbootsample.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * @author oswizar
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String lang = httpServletRequest.getParameter("lang");
        // 设置为浏览器默认语言环境
        Locale locale = Locale.getDefault();
        // 如果请求中有参数指定,则使用请求参数的
        if(!StringUtils.isEmpty(lang)) {
            String[] split = lang.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
