package com.oswizar.springbootsample.service.impl;

import com.oswizar.springbootsample.config.annotation.OperationLogDetail;
import com.oswizar.springbootsample.config.enumeration.OperationType;
import com.oswizar.springbootsample.config.enumeration.OperationUnit;
import com.oswizar.springbootsample.mapper.UserMapper;
import com.oswizar.springbootsample.service.TestIService;
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
    private UserMapper userMapper;


    @Override
    @OperationLogDetail(level = 3, operationUnit = OperationUnit.USER, operationType = OperationType.SELECT)
    public Map<String, Object> operationLog(Map params, List list) throws Exception {
//        throw new Exception("ceshi");
        params.put("new", "parameter");
        return params;
    }
}
