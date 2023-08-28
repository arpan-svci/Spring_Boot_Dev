package com.luv2code.RestController.rest;

import com.luv2code.RestController.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    // define endpoints "/student" return list of all students

    @GetMapping("/students")
    public List<Student> getStudents(){
        // creating list of students
        List<Student> theStudents= new ArrayList<>();

        // add data to the list
        theStudents.add(new Student("Arpan","Mandal"));
        theStudents.add(new Student("Satabda","Mandal"));
        theStudents.add(new Student("Rahul","Manna"));

        // returning the data
        return theStudents;
    }
}
