package com.luv2code.springboot.CrudDemo.rest;

import com.luv2code.springboot.CrudDemo.entity.Employee;
import com.luv2code.springboot.CrudDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    // private EmployeeDAO employeeDAO;
    private EmployeeService employeeService;
    // quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // add GET mapping for /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee=employeeService.findById(employeeId);
        if(theEmployee==null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        return theEmployee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // also just in case they pass an id in JSON ... set id to 0
        // that is to force a save of new item ... instead of update
        theEmployee.setId(0);
        Employee dbEmployee= employeeService.saveEmployee(theEmployee);
        return dbEmployee;
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee theEmployee,@PathVariable int employeeId){
        Employee emp=employeeService.findById(employeeId);
        if(emp==null){
            throw new RuntimeException("Employee with this id is not exist");
        }
        theEmployee.setId(employeeId);
        Employee dbEmployee=employeeService.saveEmployee(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee theEmployee=employeeService.findById(employeeId);
        if(theEmployee==null){
            throw  new RuntimeException("Employee with this id is not exist");
        }
        employeeService.deleteById(employeeId);
        return theEmployee.toString()+"- deleted successfully";
    }
}
