package com.xiexing.springbootdemo.service.impl;

import com.xiexing.springbootdemo.config.datasource.TargetDataSource;
import com.xiexing.springbootdemo.dao.DepartmentMapper;
import com.xiexing.springbootdemo.dao.TestMapper;
import com.xiexing.springbootdemo.entity.AppAdvice;
import com.xiexing.springbootdemo.entity.Department;
import com.xiexing.springbootdemo.service.TestIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @date: 2019/4/3 17:11
 * @author: oswizar
 * @description:
 */

@Service
public class TestServiceImpl implements TestIService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @TargetDataSource("ORACLE")
    @Override
    public List<AppAdvice> queryWfiAppAdvice(Map param) {
        List list = testMapper.queryWfiAppAdviceHistory(param);
        return list;
    }

    @TargetDataSource("MYSQL")
    @Override
    public Department queryDepartment(String id) {
        return departmentMapper.queryDepartment(id);
    }
}
