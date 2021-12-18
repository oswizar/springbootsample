/**
 * Copyright (C): 长安新生(深圳)金融投资有限公司
 * FileName: EmployeeDao
 * Author:   oswizar
 * Date:     2019/1/21 15:37
 * Description:
 */
package com.oswizar.springbootsample.dao;

import com.oswizar.springbootsample.entity.Department;
import com.oswizar.springbootsample.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employeeMap = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employeeMap = new HashMap<Integer,Employee>();

        employeeMap.put(1001,new Employee(1001,"E-AA","aa@163.com",1,new Department(101,"D-AA")));
        employeeMap.put(1002,new Employee(1002,"E-BB","aa@163.com",1,new Department(102,"D-BB")));
        employeeMap.put(1003,new Employee(1003,"E-CC","aa@163.com",0,new Department(103,"D-CC")));
        employeeMap.put(1004,new Employee(1004,"E-DD","aa@163.com",0,new Department(104,"D-DD")));
        employeeMap.put(1005,new Employee(1005,"E-EE","aa@163.com",1,new Department(105,"D-EE")));
    }


    public Collection<Employee> getEmployeeMap() {
        return employeeMap.values();
    }

    private static  Integer initId = 1006;

    public void save(Employee employee) {
        // 添加操作才执行if block
        if(employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));

        employeeMap.put(employee.getId(),employee);
    }

    public Employee get(Integer id) {
        return employeeMap.get(id);
    }

    public void delete(Integer id) {
        employeeMap.remove(id);
    }




}
