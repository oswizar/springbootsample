package com.xiexing.springbootdemo.controller;

import com.xiexing.springbootdemo.entity.AppAdvice;
import com.xiexing.springbootdemo.entity.Department;
import com.xiexing.springbootdemo.service.TestIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: 2019/4/3 17:14
 * @author: oswizar
 * @description:
 */
@Controller
@Slf4j
public class TestController {

    @Autowired
    private TestIService testService;

    @ResponseBody
    @RequestMapping("/testInterfaceMybatis")
    public Map testInterfaceMybatis() {
        Map result = new HashMap();
        Map param = new HashMap();
        param.put("instanceId","8F4F7DD756A90823");
        List<AppAdvice> appAdvices = new ArrayList<>();
        appAdvices = testService.queryWfiAppAdvice(param);
        log.info("查询数据库返回=================>{}",appAdvices.toString());
        result.put("data",appAdvices);
        return result;
    }

    @ResponseBody
    @RequestMapping("/testDataSource")
    public Map testDataSource() {
        Map result = new HashMap();
        Department department = new Department();
        department = testService.queryDepartment("1");
        log.info("查询数据库返回=================>{}",department.toString());
        result.put("data",department);
        return result;
    }


    @ResponseBody
    @RequestMapping("/test")
    public Map test() {
        Map result = new HashMap();
        result.put("data","1111111111111111111111111111111");
        return result;
    }
}
