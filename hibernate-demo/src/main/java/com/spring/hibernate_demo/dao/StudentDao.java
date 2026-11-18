package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.Student;
import java.util.List;

/**
 * Data Access Object (DAO) interface for performing database operations
 * on {@link Student} entities.
 */
public interface StudentDao {

    /**
     * Persists a new student entity to the database.
     *
     * @param theStudent the {@link Student} object to save
     */
    void save(Student theStudent);

    /**
     * Retrieves a student from the database by their unique ID.
     *
     * @param id the primary key identifier of the student
     * @return the matching {@link Student}, or {@code null} if not found
     */
    Student findById(Integer id);

    /**
     * Retrieves all students stored in the database.
     *
     * @return a {@link List} of all {@link Student} entities
     */
    List<Student> findAll();

    /**
     * Retrieves all students matching a given last name.
     *
     * @param lastname the last name to filter by
     * @return a {@link List} of matching {@link Student} entities
     */
    List<Student> findByLastName(String lastname);

    /**
     * Updates an existing student record in the database.
     *
     * @param theStudent the {@link Student} entity with updated fields
     */
    void update(Student theStudent);

    /**
     * Deletes a student record from the database by their unique ID.
     *
     * @param id the primary key identifier of the student to delete
     */
    void delete(Integer id);

    /**
     * Deletes all student records from the database.
     *
     * @return the number of rows deleted
     */
    int deleteAll();
}