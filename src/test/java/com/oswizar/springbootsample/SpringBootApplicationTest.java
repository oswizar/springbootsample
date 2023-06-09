package com.oswizar.springbootsample;

import com.oswizar.springbootsample.controller.HelloWorldController;
import com.oswizar.springbootsample.util.HttpUtils;
import com.oswizar.springbootsample.util.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
public class SpringBootApplicationTest {


    private MockMvc mvc;
    @Autowired
    DataSource dataSource;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }

    @Test
    public void helloWorld() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andExpect(content().string(equalTo("Hello World")));

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


    // 编程技巧:使用equals判断的时候,要把已知的变量写在前边,未知的写在后边,防止空指针异常
    @Test
    public void equalsTest() {
        String index = null;
        if ("oooo".equals(index)) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }


    @Test
    public void sendTest() {
//        System.out.println(HttpUtils.senPostParmaStr("http://www.baidu.com",""));
        System.out.println(HttpUtils.senPost("http://www.baidu.com", ""));
        System.out.println("=========================================================================");
        System.out.println(HttpUtils.sendPost("http://www.baidu.com", ""));


    }

    @Test
    public void md5Test() {

        String md5String = Md5Utils.getMd5String("3857662@#%%9Od02Jd?//```d1");
        System.out.println(md5String);
        Integer[] sum = new Integer[]{1, 2, 3};

        for (int i : sum) {
            System.out.println(i);

        }

        List list = Arrays.asList(sum);
        list.forEach(o -> System.out.println(o));

    }


}


