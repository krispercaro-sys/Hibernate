package com.spring.hibernate_demo.dao;

import java.util.List;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object interface defining CRUD and relationship query
 * operations for Instructor, InstructorDetail, Course, Review, and
 * Student entities.
 */
public interface AppDao {

    /**
     * Persists a new instructor entity to the database.
     *
     * @param theInstructor the instructor entity to save
     */
    void save(Instructor theInstructor);

    /**
     * Retrieves an instructor by their primary key ID.
     *
     * @param theId the unique ID of the instructor
     * @return the matching Instructor entity, or null if not found
     */
    Instructor findInstructorById(int theId);

    /**
     * Deletes an instructor and cascaded details by primary key ID.
     *
     * @param theId the unique ID of the instructor to delete
     */
    void deleteInstructorId(int theId);

    /**
     * Retrieves instructor detail information by its primary key ID.
     *
     * @param theId the unique ID of the instructor detail record
     * @return the matching InstructorDetail entity, or null if not
     *         found
     */
    InstructorDetail findInstructorDetailById(int theId);

    /**
     * Deletes an instructor detail record by its primary key ID.
     *
     * @param theId the unique ID of the instructor detail to delete
     */
    void deleteInstructorDetail(int theId);

    /**
     * Retrieves all courses associated with a specific instructor.
     *
     * @param theId the unique ID of the instructor
     * @return a list of Course entities taught by the instructor
     */
    List<Course> findCoursesByInstructorId(int theId);

    /**
     * Eagerly fetches an instructor along with their associated
     * courses using a JOIN FETCH query.
     *
     * @param theId the unique ID of the instructor
     * @return the Instructor entity with initialized courses
     */
    Instructor findInstructorByIdJoinFetch(int theId);

    /**
     * Saves an instructor along with their associated courses.
     *
     * @param theInstructor the instructor entity containing courses
     */
    void saveInstructorWithCourses(Instructor theInstructor);

    /**
     * Updates an existing instructor entity in the database.
     *
     * @param instructor the instructor entity with updated fields
     */
    void update(Instructor instructor);

    /**
     * Deletes an instructor by ID without deleting their associated
     * courses.
     *
     * @param theId the unique ID of the instructor to delete
     */
    void deleteInstructorById(int theId);

    /**
     * Persists a new course entity to the database.
     *
     * @param theCourse the course entity to save
     */
    void save(Course theCourse);

    /**
     * Retrieves a course along with its associated reviews by
     * course ID.
     *
     * @param theId the unique ID of the course
     * @return the Course entity with initialized reviews
     */
    Course findCourseAndReviewsByCourseId(int theId);

    /**
     * Deletes a course record by its primary key ID.
     *
     * @param theId the unique ID of the course to delete
     */
    void deleteCourseById(int theId);

    /**
     * Retrieves a course along with its enrolled students by
     * course ID.
     *
     * @param theId the unique ID of the course
     * @return the Course entity with initialized students
     */
    Course findCourseAndStudentByCourseId(int theId);

    /**
     * Retrieves a student along with their enrolled courses by
     * student ID.
     *
     * @param theId the unique ID of the student
     * @return the Student entity with initialized courses
     */
    Student findStudentAndCoursesByStudentId(int theId);

    /**
     * Updates an existing student entity in the database.
     *
     * @param student the student entity with updated fields
     */
    void update(Student student);

    /**
     * Deletes a student record by its primary key ID.
     *
     * @param theId the unique ID of the student to delete
     */
    void deleteStudentById(int theId);

}