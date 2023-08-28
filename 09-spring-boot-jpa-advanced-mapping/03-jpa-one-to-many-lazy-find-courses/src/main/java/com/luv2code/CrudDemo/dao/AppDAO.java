package com.luv2code.CrudDemo.dao;

import com.luv2code.CrudDemo.entity.Course;
import com.luv2code.CrudDemo.entity.Instructor;
import com.luv2code.CrudDemo.entity.InstructorDetail;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void updateInstructor(Instructor tempInstructor);

    void updateCourse(Course tempCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

}
