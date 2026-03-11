package com.oswizar.springbootsample.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.oswizar.springbootsample.config.ConfigConstants;
import com.oswizar.springbootsample.model.LoginUser;
import com.oswizar.springbootsample.util.RedisUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String ROOT_USERNAME = "root";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        JWT jwt;
        try {
            jwt = JWTUtil.parseToken(token);
        } catch (Exception e) {
            throw new RuntimeException("token解析异常");
        }
        String username = (String) jwt.getPayload("username");
        
        // root用户token永不过期，跳过过期时间验证
        boolean isRootUser = ROOT_USERNAME.equals(username);
        boolean validate;
        if (isRootUser) {
            boolean hasExp = jwt.getPayload("exp") != null;
            if (hasExp) {
                validate = jwt.setKey(ConfigConstants.JWT_SECRET_KEY.getBytes()).validate(0);
            } else {
                validate = jwt.setKey(ConfigConstants.JWT_SECRET_KEY.getBytes()).validate(-1);
            }
        } else {
            validate = jwt.setKey(ConfigConstants.JWT_SECRET_KEY.getBytes()).validate(0);
        }

        if (!validate) {
            throw new RuntimeException("token验证异常");
        }
        // 根据解析出来的用户名到缓存中获取用户信息
        String userKey = "login:" + username;
        LoginUser loginUser = (LoginUser) RedisUtils.get(userKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("JwtAuthenticationTokenFilter从缓存获取用户信息为空");
        }
        // 存入SecurityContextHolder
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
