package com.spring.hibernate_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Review;
import com.spring.hibernate_demo.entity.Student;

/**
 * Main entry point for the Hibernate Advance Demo Spring Boot
 * application. Demonstrates entity relationships (One-to-One,
 * One-to-Many, Many-to-Many) and CRUD operations via AppDao.
 */
@SpringBootApplication
public class HibernateAdvanceDemoApplication {

    /**
     * Launches the Spring Boot application.
     *
     * @param args command-line arguments passed at startup
     */
    public static void main(String[] args) {
        SpringApplication.run(
                HibernateAdvanceDemoApplication.class, args);
    }

    /**
     * Defines the command-line runner bean executed on startup.
     *
     * @param app the data access object for database operations
     * @return a configured CommandLineRunner instance
     */
    @Bean
    CommandLineRunner commandLineRunner(AppDao app) {
        return runner -> {
            // createInstructor(app);
            // findInstructor(app);
            // deleteInstructor(app);
            // findInstructorDetail(app);
            // deleteInstructorDetails(app);
            // createInstructorWithCourses(app);
            // findInstructorWithCourses(app);
            // findInstructorWithCoursesJoinFetch(app);
            // updateInstructor(app);
            // deleteInstructorOnly(app);
            // createCourseAndRreviews(app);
            // retrieveCourseAndReviews(app);
            // deleteCourseAndReviews(app);
            // createCourseAndStudent(app);
            // findCourseAndStudents(app);
            // findStudentAndCourses(app);
            // addMoreCoursesForStudent(app);
            // deleteCourseById(app);
            deleteStudent(app);
        };
    }

    /**
     * Deletes a student record by ID without deleting associated
     * courses.
     *
     * @param app the data access object
     */
    public void deleteStudent(AppDao app) {
        int id = 26;
        System.out.println("Delete Student: " + id);

        app.deleteStudentById(id);
        System.out.println("Deleted Successfully!");
    }

    /**
     * Finds an existing student and enrolls them in additional new
     * courses.
     *
     * @param app the data access object
     */
    private void addMoreCoursesForStudent(AppDao app) {
        int id = 48;

        Student student =
                app.findStudentAndCoursesByStudentId(id);

        Course course1 = new Course(
                "How to Test without Running Application");
        Course course2 = new Course(
                "How to develop without pc");
        Course course3 = new Course(
                "Am I a legit Dev???");

        student.addCourse(course1);
        student.addCourse(course2);
        student.addCourse(course3);

        System.out.println(
                "Successfully enrolled to new courses!");

        app.update(student);
    }

    /**
     * Deletes a course record by its primary key ID.
     *
     * @param app the data access object
     */
    private void deleteCourseById(AppDao app) {
        int id = 27;
        System.out.println("Delete course: " + id);

        app.deleteCourseById(id);
        System.out.println("Deleted Successfully!");
    }

    /**
     * Retrieves a student and their enrolled courses by student ID.
     *
     * @param app the data access object
     */
    private void findStudentAndCourses(AppDao app) {
        int id = 48;
        Student student =
                app.findStudentAndCoursesByStudentId(id);

        System.out.println("Student: " + student);
        System.out.println("Course: " + student.getCourses());
    }

    /**
     * Retrieves a course and its enrolled students by course ID.
     *
     * @param app the data access object
     */
    private void findCourseAndStudents(AppDao app) {
        int id = 24;
        Course course =
                app.findCourseAndStudentByCourseId(id);

        System.out.println("Course: " + course);
        System.out.println("Students: " + course.getStudents());
    }

    /**
     * Creates a new course along with two students and persists
     * them.
     *
     * @param app the data access object
     */
    private void createCourseAndStudent(AppDao app) {
        Course course = new Course(
                "How to Be A FullStack Dev in Half A Day");

        Student student1 = new Student(
                "Light", "Yagami", "light.yagami@example.com");
        Student student2 = new Student(
                "Leslie", "Andres", "leslie.andrews@example.com");

        course.addStudent(student1);
        course.addStudent(student2);

        app.save(course);
    }

    /**
     * Deletes a course and cascades the deletion to its reviews.
     *
     * @param app the data access object
     */
    private void deleteCourseAndReviews(AppDao app) {
        int id = 1;
        System.out.println("Delete course: " + id);

        app.deleteCourseById(id);
        System.out.println("Delete successfully!");
    }

    /**
     * Retrieves a course and its associated reviews by course ID.
     *
     * @param app the data access object
     */
    private void retrieveCourseAndReviews(AppDao app) {
        int id = 10;

        System.out.println(
                "Get course and reviews from id: " + id);

        Course course =
                app.findCourseAndReviewsByCourseId(id);

        System.out.println(course);
        System.out.println(course.getReview());
    }

    /**
     * Creates a new course with multiple reviews and saves it.
     *
     * @param app the data access object
     */
    private void createCourseAndRreviews(AppDao app) {
        Course course = new Course(
                "How to Speed Run Spring in 1 day");

        course.addReview(
                new Review("Great speed 2x...loved it"));
        course.addReview(
                new Review("I wanted the speed 0.75 :(("));
        course.addReview(new Review(
                "Hoping to do a speed of 3x :D. Anyways, nice!"));

        System.out.println("Course saved!");
        app.save(course);
    }

    /**
     * Deletes an instructor without cascading deletion to their
     * courses.
     *
     * @param app the data access object
     */
    private void deleteInstructorOnly(AppDao app) {
        int id = 8;
        System.out.println("Delete instructor: " + id);

        app.deleteInstructorById(id);
        System.out.println("Deleted Successfully");
    }

    /**
     * Finds an instructor by ID and updates their last name.
     *
     * @param app the data access object
     */
    private void updateInstructor(AppDao app) {
        int id = 8;
        System.out.println("Find instructor id: " + id);

        Instructor ins = app.findInstructorById(id);

        ins.setLastName("Cosma");

        System.out.println("Update!");
        app.update(ins);
    }

    /**
     * Retrieves an instructor and their courses eagerly using
     * JOIN FETCH.
     *
     * @param app the data access object
     */
    private void findInstructorWithCoursesJoinFetch(AppDao app) {
        int id = 8;
        System.out.println("Find instructor id: " + id);

        Instructor ins = app.findInstructorByIdJoinFetch(id);

        System.out.println("Instructor: " + ins);
        System.out.println("Course: " + ins.getCourses());
    }

    /**
     * Retrieves an instructor by ID and accesses their associated
     * courses.
     *
     * @param app the data access object
     */
    private void findInstructorWithCourses(AppDao app) {
        int id = 10;
        System.out.println("Find instrucotr id: " + id);

        Instructor ins = app.findInstructorById(id);

        System.out.println("Instructor: " + ins);
        System.out.println("Course: " + ins.getCourses());
    }

    /**
     * Creates an instructor with details and multiple courses, then
     * saves them.
     *
     * @param app the data access object
     */
    private void createInstructorWithCourses(AppDao app) {
        Instructor ins = new Instructor(
                "Peri", "Winkle", "peri.winkle@example.com");
        InstructorDetail deets = new InstructorDetail(
                "Science", "Sprinkling dust");

        ins.setInstructorDeets(deets);

        Course course1 = new Course("Physics");
        Course course2 = new Course("Biology");

        ins.add(course1);
        ins.add(course2);

        app.saveInstructorWithCourses(ins);

        System.out.println("Course saved: " + ins.getCourses());
    }

    /**
     * Deletes an instructor detail record by its primary key ID.
     *
     * @param app the data access object
     */
    private void deleteInstructorDetails(AppDao app) {
        int id = 6;
        System.out.println("Delete od: " + id);

        app.deleteInstructorDetail(id);
        System.out.println("Deleted successfully!");
    }

    /**
     * Finds an instructor detail record and prints the associated
     * instructor.
     *
     * @param app the data access object
     */
    private void findInstructorDetail(AppDao app) {
        int id = 6;
        InstructorDetail inDeets =
                app.findInstructorDetailById(id);

        System.out.println("Details: " + inDeets);
        System.out.println(
                "Instructor: " + inDeets.getInstructor());
    }

    /**
     * Deletes an instructor record by ID.
     *
     * @param app the data access object
     */
    private void deleteInstructor(AppDao app) {
        int id = 3;
        System.out.println("Delete id: " + id);

        app.deleteInstructorId(id);
        System.out.println("Deleted successsfully!");
    }

    /**
     * Retrieves an instructor by ID and prints their profile
     * details.
     *
     * @param app the data access object
     */
    private void findInstructor(AppDao app) {
        int id = 1;
        System.out.println("Find id: " + id);

        Instructor ins = app.findInstructorById(id);

        System.out.println("Instructor:" + ins);
        System.out.println(
                "Details: " + ins.getInstructorDeets());
    }

    /**
     * Creates a new instructor with associated profile details and
     * persists it.
     *
     * @param app the data access object
     */
    private void createInstructor(AppDao app) {
        Instructor ins = new Instructor(
                "Vicky", "Magtanggol", "vicky.mags@example.com");

        InstructorDetail deets = new InstructorDetail(
                "Math", "Solving problems");

        // Now setInstructorDeets works because the setter is
        // defined in Instructor
        ins.setInstructorDeets(deets);

        System.out.println("Saving instructor: " + ins);
        app.save(ins);
        System.out.println("Information saved!");
    }
}