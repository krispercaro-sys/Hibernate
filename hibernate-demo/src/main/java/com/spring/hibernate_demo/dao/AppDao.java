package com.spring.hibernate_demo.dao;

import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Instructor;

import java.util.List;

import com.spring.hibernate_demo.entity.Course;


public interface AppDao {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorId(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetail(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    void saveInstructorWithCourses(Instructor theInstructor);

}
