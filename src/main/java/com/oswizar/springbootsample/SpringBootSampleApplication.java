package com.oswizar.springbootsample;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@MapperScan("com.oswizar.springbootsample.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootSampleApplication {

//	@Bean
//	public RestTemplate createHttpsRestTemplate() {
//		return new RestTemplate();
//	}

//    @Bean
//    public RedisTemplate createRedisTemplate() {
//        return new RedisTemplate();
//    }

    public static void main(String[] args) {

        // 防止邮件中附件由于文件名称过长出现乱码
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");

        SpringApplication.run(SpringBootSampleApplication.class, args);
    }
}

