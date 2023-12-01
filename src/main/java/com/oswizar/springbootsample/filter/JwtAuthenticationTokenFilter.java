package com.oswizar.springbootsample.filter;

import cn.hutool.jwt.JWTUtil;
import com.oswizar.springbootsample.entity.LoginUser;
import com.oswizar.springbootsample.util.RedisUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String username;
        try {
            username = (String) JWTUtil.parseToken(token).getPayload("username");
        } catch (Exception e) {
            throw new RuntimeException("token解析异常");
        }
        // 根据解析出来的用户名到缓存中获取用户信息
        String userKey = "login:" + username;
        LoginUser loginUser = (LoginUser) RedisUtils.get(userKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("JwtAuthenticationTokenFilter获取用户信息失败");
        }
        // 存入SecurityContextHolder
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
