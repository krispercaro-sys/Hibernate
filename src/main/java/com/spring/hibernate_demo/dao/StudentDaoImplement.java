package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * DAO implementation for managing Student entity persistence.
 */
@Repository
public class StudentDaoImplement implements StudentDao {

    private EntityManager em;

    /**
     * Constructs a StudentDaoImplement with the given EntityManager.
     *
     * @param em the JPA EntityManager
     */
    public StudentDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * Persists a new Student entity.
     *
     * @param theStudent the student to save
     */
    @Override
    @Transactional
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    /**
     * Updates an existing Student entity.
     *
     * @param theStudent the student to update
     */
    @Override
    @Transactional
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    /**
     * Deletes a Student record by its primary key ID.
     *
     * @param id the student ID
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
            System.out.println("Deleted student with ID: " + id);
        } else {
            System.out.println(
                    "Student with ID " + id + " not found!");
        }
    }

    /**
     * Deletes all Student records from the database.
     *
     * @return the number of rows deleted
     */
    @Override
    @Transactional
    public int deleteAll() {
        return em.createQuery("DELETE FROM Student").executeUpdate();
    }

    /**
     * Finds a Student by ID.
     *
     * @param id the student ID
     * @return the found Student or null
     */
    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    /**
     * Retrieves all Students ordered ascending by last name.
     *
     * @return list of all Students
     */
    @Override
    public List<Student> findAll() {
        return em.createQuery(
                "FROM Student ORDER BY lastName asc",
                Student.class).getResultList();
    }

    /**
     * Finds Students matching a specific last name.
     *
     * @param lastname the last name to filter by
     * @return list of matching Students
     */
    @Override
    public List<Student> findByLastName(String lastname) {
        TypedQuery<Student> query = em.createQuery(
                "FROM Student WHERE lastName = :data",
                Student.class);
        query.setParameter("data", lastname);
        return query.getResultList();
    }
}