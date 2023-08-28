package com.luv2code.CrudDemo.dao;

import com.luv2code.CrudDemo.entity.Instructor;
import com.luv2code.CrudDemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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

}
