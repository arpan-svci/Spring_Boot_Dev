package com.luv2code.springboot.CrudDemo.service;

import com.luv2code.springboot.CrudDemo.entity.Employee;

import java.util.List;


public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theId);

    Employee saveEmployee(Employee theEmployee);

    void deleteById(int theId);
}
