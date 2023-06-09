package com.oswizar.springbootsample.mapper;

import com.oswizar.springbootsample.entity.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {

    Department queryDepartment(String id);


}
