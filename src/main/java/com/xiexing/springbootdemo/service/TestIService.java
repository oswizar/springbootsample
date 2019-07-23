package com.xiexing.springbootdemo.service;

import com.xiexing.springbootdemo.entity.AppAdvice;
import com.xiexing.springbootdemo.entity.Department;

import java.util.List;
import java.util.Map;

public interface TestIService {

    Map queryWfiAppAdvice();

    Department queryDepartment(String id);
}
