package com.xiexing.springbootdemo.dao;

import com.xiexing.springbootdemo.entity.AppAdvice;
import com.xiexing.springbootdemo.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentMapper {


    /**
     * @Description :查看审批轨迹
     * @Author : Wuhengzhen
     * @date : 2017/11/6 11:14
     */

    Department queryDepartment(String id);


}