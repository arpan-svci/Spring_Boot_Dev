package com.luv2code.springboot.CrudDemo.dao;

import com.luv2code.springboot.CrudDemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int theId);

    Employee saveEmployee(Employee theEmployee);

    void deleteById(int theId);
}
