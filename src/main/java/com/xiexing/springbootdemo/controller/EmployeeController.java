/**
 * Copyright (C): 长安新生(深圳)金融投资有限公司
 * FileName: EmployeeController
 * Author:   xiexing
 * Date:     2019/3/14 10:28
 * Description:
 */
package com.xiexing.springbootdemo.controller;

import com.xiexing.springbootdemo.dao.DepartmentDao;
import com.xiexing.springbootdemo.dao.EmployeeDao;
import com.xiexing.springbootdemo.entity.Department;
import com.xiexing.springbootdemo.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;
@Slf4j
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    /**
     * 员工列表展示
     * @param map
     * @return
     */
    @GetMapping("/emps")
    public String list(Map<String,Object> map) {
        Collection<Employee> employeeMap = employeeDao.getEmployeeMap();
        log.info("查询出来的信息为==============:{}",employeeMap);
        map.put("emps",employeeMap);
        // thymeleaf默认拼接路径(classpath:/templates/)
        return "emp/list";
    }


    /**
     * 跳转到员工添加页面
     * @param map
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Map<String,Object> map) {
        // 查询出部门信息,供页面的下拉框动态加载
        Collection<Department> departmentMap = departmentDao.getDepartmentMap();
        map.put("departments",departmentMap);
        // 跳转到员工添加页面
        return "emp/add";
    }

    /**
     * 员工添加操作
     * SpringMVC 自动将请求参数与入参对象的属性一一绑定,要求请求参数的名字要与对象的属性名相同
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        log.info("新增员工的信息为:=========={}",employee);
        // 保存员工信息
        employeeDao.save(employee);
        // redirect,表示重定向到一个地址,/当前项目路径
        // forward,表示转发到一个地址
        return "redirect:/emps";
    }

    /**
     * 跳转到修改页面,查出当前员工信息供页面回显,也要查询出部门列表供下拉框显示
     * @param id
     * @param map
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Map<String,Object> map) {
        // 根据id查询员工信息
        Employee employee = employeeDao.get(id);
        // 查询部门列表
        Collection<Department> departmentMap = departmentDao.getDepartmentMap();
        map.put("employee",employee);
        map.put("departments",departmentMap);
        // 跳转到员工修改页面,add页面集添加/修改二合一
        return "emp/add";
    }

    /**
     * 员工修改
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String updateEmp(Employee employee) {
        log.info("修改的员工信息为============={}",employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 员工删除
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        log.info("删除员工编号为============={}",id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
