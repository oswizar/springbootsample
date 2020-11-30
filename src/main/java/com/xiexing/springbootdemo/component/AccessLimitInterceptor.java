package com.xiexing.springbootdemo.component;

import com.xiexing.springbootdemo.util.IPUtils;
import com.xiexing.springbootdemo.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AccessLimitInterceptor implements HandlerInterceptor {

    //使用RedisTemplate操作redis
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 接口请求限制次数
     */
    @Value("${api-access-limit-count}")
    private int limit;

    /**
     * 接口请求时间限制(秒)
     */
    @Value("${api-access-limit-sec}")
    private int sec;

    /**
     * 定义需要拦截的接口前缀列表
     */
    private String[] accessLimitUrlList = {"/accessControl", "/redisGetValues"};


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
//            if (!method.isAnnotationPresent(AccessLimit.class)) {
//                return true;
//            }
//            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
//            if (accessLimit == null) {
//                return true;
//            }

//            int limit = accessLimit.limit();
//            int sec = accessLimit.sec();

        // 定义拦截标示
        boolean accessLimitFlag = false;

        // 获取请求接口的名称
        String currentUrl = request.getServletPath();
        for (String accessLimitUrl : accessLimitUrlList) {
            // 检测当前请求接口是否需要拦截
            if (currentUrl.startsWith(accessLimitUrl)) {
                accessLimitFlag = true;
                break;
            }
        }

        if (!accessLimitFlag) {
        } else {
            String key = IPUtils.getIpAddr(request) + ":" + request.getServerPort() + request.getRequestURI();
            Integer maxLimit = (Integer) redisTemplate.opsForValue().get(key);
//            Integer maxLimit = (Integer) redisUtils.get(key);
            if (maxLimit == null) {
                //set时一定要加过期时间
                redisTemplate.opsForValue().set(key, 1, sec, TimeUnit.SECONDS);
//                redisUtils.set(key, 1, sec);
            } else if (maxLimit < limit) {
                redisTemplate.opsForValue().set(key, maxLimit + 1, sec, TimeUnit.SECONDS);
//                redisUtils.set(key, maxLimit + 1, sec);
            } else {
                output(response, "请求太频繁,请稍后再试!");
                return false;
            }
        }
        return true;
//        }

    }

    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}