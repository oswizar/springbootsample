package com.oswizar.springbootsample.service.impl;

import com.oswizar.springbootsample.config.datasource.TargetDataSource;
import com.oswizar.springbootsample.entity.Department;
import com.oswizar.springbootsample.mapper.DepartmentMapper;
import com.oswizar.springbootsample.service.TestIService;
import com.oswizar.springbootsample.annotation.OperationLogDetail;
import com.oswizar.springbootsample.enumeration.OperationType;
import com.oswizar.springbootsample.enumeration.OperationUnit;
import com.oswizar.springbootsample.mapper.TestMapper;
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
    public Map queryWfiAppAdvice() {
        Map list = testMapper.queryWfiAppAdviceHistory();
        return list;
    }

    @TargetDataSource("MYSQL")
    @Override
    public Department queryDepartment(String id) {
        return departmentMapper.queryDepartment(id);
    }


    @Override
    @OperationLogDetail(level = 3, operationUnit = OperationUnit.USER, operationType = OperationType.SELECT)
    public Map<String, Object> operationLog(Map params, List list) throws Exception {
//        throw new Exception("ceshi");
        params.put("new", "parameter");
        return params;
    }
}
