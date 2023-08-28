package com.luv2code.springboot.CrudDemo.dao;

import com.luv2code.springboot.CrudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    // no need to write any code here
}
