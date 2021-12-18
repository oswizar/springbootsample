/**
 * Copyright (C): 长安新生(深圳)金融投资有限公司
 * FileName: Employee
 * Author:   oswizar
 * Date:     2019/1/21 15:36
 * Description:
 */
package com.oswizar.springbootsample.entity;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Employee {

    private Integer id;

    @NotEmpty
    private String lastName;

    @Email
    private String email;

    @NumberFormat(pattern = "#,###,###.#")
    private float salary;

//    @Past
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 默认格式为yyyy/MM/dd,而不是yyyy-MM-dd
    private Date birth;
    /**
     * 1 male, 0 female
     */
    private Integer gender;
    private Department department;

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = new Date();
    }

    public Employee(Integer id, String lastName, String email, float salary, Date birth, Integer gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.birth = birth;
        this.gender = gender;
        this.department = department;
    }


    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", birth=" + birth +
                ", gender=" + gender +
                ", department=" + department +
                '}';
    }
}
