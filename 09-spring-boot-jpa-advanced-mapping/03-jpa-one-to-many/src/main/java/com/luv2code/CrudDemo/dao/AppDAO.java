package com.luv2code.CrudDemo.dao;

import com.luv2code.CrudDemo.entity.Instructor;
import com.luv2code.CrudDemo.entity.InstructorDetail;
import org.springframework.stereotype.Component;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
