package com.oswizar.springbootsample.service;

import com.oswizar.springbootsample.entity.Department;

import java.util.List;
import java.util.Map;

public interface TestIService {

    Map queryWfiAppAdvice();

    Department queryDepartment(String id);

    Map<String, Object> operationLog(Map params, List list) throws Exception;
}
