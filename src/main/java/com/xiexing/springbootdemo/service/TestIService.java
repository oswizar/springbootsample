package com.xiexing.springbootdemo.service;

import com.xiexing.springbootdemo.entity.AppAdvice;
import com.xiexing.springbootdemo.entity.Department;

import java.util.List;
import java.util.Map;

public interface TestIService {

    List<AppAdvice> queryWfiAppAdvice(Map param);

    Department queryDepartment(String id);
}
