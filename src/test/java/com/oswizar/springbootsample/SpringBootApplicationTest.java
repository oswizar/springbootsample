package com.oswizar.springbootsample;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.oswizar.springbootsample.util.HttpUtils;
import com.oswizar.springbootsample.util.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
public class SpringBootApplicationTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testPassword() {
        String s = "1234";
        System.out.println(passwordEncoder.encode(s));
    }

    @Test
    public void testJwt() {
        String subject = JWT.create().setSubject("hello").setSigner(JWTSignerUtil.none()).sign();
        System.out.println(subject);

        JWT parseToken = JWTUtil.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InJvb3QifQ.VgTKQxgvMk2wpDm0k-pM5tvrg9Knz-gFSD7dueWTHPY");
        System.out.println(parseToken.getPayload());
    }

    @Test
    public void dataBundleTest() {
        String str = "dsdfsdfkjsfiej235";
        String subStr = str.substring(1, 6);
        System.out.println(subStr);
        System.out.println(str);
    }

    /**
     * 级别由低到高 trace<debug<info<warn<error
     * 可以调整输出日志的级别,日志就只会在这个级别及以上的级别生效
     * SpringBoot 默认日志级别为info
     */
    @Test
    public void logginTest() {
        log.trace("log.trace");
        log.debug("log.debug");
        log.info("log.info");
        log.warn("log.warn");
        log.error("log.error");
    }

    @Test
    public void contextLoad() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(dataSource.getClass());
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void md5Test() {
        String md5String = Md5Utils.getMd5String("3857662@#%%9Od02Jd?//```d1");
        System.out.println(md5String);
        Integer[] sum = new Integer[]{1, 2, 3};
        for (int i : sum) {
            System.out.println(i);
        }
    }


    public static void main(String[] args) {

//        System.out.println(11111);
//        Snowflake snowflake = IdUtil.getSnowflake(1L, 1L);
//        System.out.println(System.currentTimeMillis());
//        for (int i = 0; i < 100; i++) {
//            System.out.println(snowflake.nextId());
//        }

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss.SSS");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss.SSSSSS");
        LocalDateTime parse = LocalDateTime.parse("2023 12 01 10:51:32.167",dateTimeFormatter1);
        System.out.println(parse);

        System.out.println(dateTimeFormatter1.format(localDateTime));
        System.out.println(dateTimeFormatter2.format(localDateTime));
        Date date = Date.from(localDateTime.toInstant(ZoneOffset.UTC));
        System.out.println(ZoneId.systemDefault());
        System.out.println(new SimpleDateFormat("yyyy MM dd HH:mm:ss.SSS").format(date));


    }


}


