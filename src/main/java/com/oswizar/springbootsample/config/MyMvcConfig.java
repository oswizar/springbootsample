package com.oswizar.springbootsample.config;

import com.oswizar.springbootsample.component.LoginHandlerInteceptor;
import com.oswizar.springbootsample.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 使用WebMvcConfigurer可以来扩展SpringMVC的功能
 * 注解:@EnableWebMvc,作用完全接管SpringMVC
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
//public class MyMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 配置项目启动页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        // 配置登录成功后进入主界面,防止刷新后页面重复提交
        registry.addViewController("/main.html").setViewName("dashboard");
    }



    /**
     * 配置静态资源路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 1.设置静态资源的访问路径,直接从项目的根路径进入,路径不用加以下的包名
        registry.addResourceHandler("/**")
                // 2.添加静态资源文件的包名
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
                // 针对引入webjars包
                .addResourceLocations("classpath:/META-INF/resources/");
//        super.addResourceHandlers(registry);
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 静态资源???(*.js,*.css)
        registry.addInterceptor(new LoginHandlerInteceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index", "/index.html","/","/login","/logout"
//                        ,"/static/**"
                        ,"/asserts/**"
                        // webjars包配置
                ,"/webjars/**");
//        super.addInterceptors(registry);
    }




    /**
     * 配置自定义的国际化组件
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
