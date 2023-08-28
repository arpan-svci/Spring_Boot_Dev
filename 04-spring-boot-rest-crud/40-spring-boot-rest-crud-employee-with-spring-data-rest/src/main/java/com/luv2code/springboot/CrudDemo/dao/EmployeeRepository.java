package com.luv2code.springboot.CrudDemo.dao;

import com.luv2code.springboot.CrudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    // no need to write any code here
}
