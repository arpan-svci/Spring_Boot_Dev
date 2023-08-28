package com.luv2code.CrudDemo.dao;

import com.luv2code.CrudDemo.entity.Instructor;
import org.springframework.stereotype.Component;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);
}
