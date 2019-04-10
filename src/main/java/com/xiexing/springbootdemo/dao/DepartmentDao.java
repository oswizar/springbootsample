/**
 * Copyright (C): 长安新生(深圳)金融投资有限公司
 * FileName: DepartmentDao
 * Author:   xiexing
 * Date:     2019/1/21 15:54
 * Description:
 */
package com.xiexing.springbootdemo.dao;

import com.xiexing.springbootdemo.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departmentMap = null;

    static {
        departmentMap = new HashMap<Integer, Department>();

        departmentMap.put(101,new Department(101,"D-AA"));
        departmentMap.put(102,new Department(102,"D-BB"));
        departmentMap.put(103,new Department(103,"D-CC"));
        departmentMap.put(104,new Department(104,"D-DD"));
        departmentMap.put(105,new Department(105,"D-EE"));
    }

    public Collection<Department> getDepartmentMap() {
        return departmentMap.values();
    }

    public Department getDepartment(Integer id) {
        return departmentMap.get(id);
    }
}
