package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    @Override
    @Transactional
    public void deleteInstructorId(int theId) {

        Instructor in = em.find(Instructor.class, theId);

        em.remove(in);
    }

    @Override
    @Transactional
    public void saveInstructorWithCourses(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {

        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        deets.getInstructor().setInstructorDeets(null);

        em.remove(deets);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    @Override 
    public List<Course> findCoursesByInstructorId(int theId){
        TypedQuery<Course> query = em.createQuery("From Course WHERE instructor.id=:data",Course.class);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
