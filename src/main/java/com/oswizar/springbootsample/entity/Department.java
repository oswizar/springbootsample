/**
 * Copyright (C): 长安新生(深圳)金融投资有限公司
 * FileName: Department
 * Author:   oswizar
 * Date:     2019/1/21 15:36
 * Description:
 */
package com.oswizar.springbootsample.entity;

public class Department {

    private Integer id;
    private String departmentName;

    public Department() {
    }

    public Department(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
