package com.luv2code.CrudDemo.dao;

import com.luv2code.CrudDemo.entity.Course;
import com.luv2code.CrudDemo.entity.Instructor;
import com.luv2code.CrudDemo.entity.InstructorDetail;
import com.luv2code.CrudDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    //define field for entity manager
    private EntityManager entityManager;
    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        // find the instructor by id
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // find the instructor by id
        Instructor theInstructor=entityManager.find(Instructor.class,theId);
        // get the courses
        List<Course> courses= theInstructor.getCourses();
        // brake the association of courses for the instructor
        for(Course course:courses){
            course.setInstructor(null);
        }
        //delete the instructor
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // find the instructor detail object
        InstructorDetail tempInstructorDetail=entityManager.find(InstructorDetail.class,theId);

        // remove the associated object reference
        // break the bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // remove the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query=entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data",theId);
        List<Course> courses=query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query=entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail where i.id= :data", Instructor.class);
        query.setParameter("data",theId);
        Instructor theInstructor=query.getSingleResult();
        return theInstructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course=entityManager.find(Course.class,theId);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void saveCourse(Course theCourse) {
        entityManager.persist((theCourse));
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        // create query
        TypedQuery<Course> query=entityManager.createQuery(
                "select c from Course c JOIN FETCH c.reviews where c.id = :data", Course.class
        );
        query.setParameter("data",theId);
        // execute query
        Course course=query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        // create query
        TypedQuery<Course> query=entityManager.createQuery(
                "select c from Course c JOIN FETCH c.students where c.id= :data", Course.class);
        query.setParameter("data",theId);
        // execute query
        Course course=query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCourseByStudentId(int theId) {
        TypedQuery<Student> query=entityManager.createQuery(
                "select s from Student s JOIN FETCH s.courses where s.id= :data",Student.class
        );
        query.setParameter("data",theId);
        Student theStudent=query.getSingleResult();
        return theStudent;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student tempStudent=entityManager.find(Student.class,theId);
        entityManager.remove(tempStudent);
    }

}
