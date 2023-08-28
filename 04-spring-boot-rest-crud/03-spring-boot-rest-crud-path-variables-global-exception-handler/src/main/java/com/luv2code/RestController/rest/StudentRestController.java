package com.luv2code.RestController.rest;

import com.luv2code.RestController.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents=new ArrayList<>();

    // define @PostConstruct to load the student data
    @PostConstruct
    public void loadData(){
        // add data to the list
        theStudents.add(new Student("Arpan","Mandal"));
        theStudents.add(new Student("Satabda","Mandal"));
        theStudents.add(new Student("Rahul","Manna"));
    }

    // define endpoints "/student" return list of all students

    @GetMapping("/students")
    public List<Student> getStudents(){
        // returning the data
        return theStudents;
    }

    // define endpoint for "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        // just index into the list ... keep it simple

        // check the studentId again list size

        if((studentId>=theStudents.size())||(studentId<0)){
            throw new StudentNotFoundException("student id not found - "+studentId);
        }
        return theStudents.get(studentId);
    }
}
