package com.spring.hibernate_demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.StudentDao;
import com.spring.hibernate_demo.entity.Student;

/**
 * Main entry point for the Hibernate Demo Spring Boot application.
 * Demonstrates basic CRUD and JPQL query operations on Student
 * entities via StudentDao.
 */
@SpringBootApplication
public class HibernateDemoApplication {

    /**
     * Launches the Spring Boot application.
     *
     * @param args command-line arguments passed at startup
     */
    public static void main(String[] args) {
        SpringApplication.run(
                HibernateDemoApplication.class, args);
    }

    /**
     * Defines the command-line runner bean executed on startup.
     *
     * @param studentDao the data access object for student
     *                   operations
     * @return a configured CommandLineRunner instance
     */
    @Bean
    CommandLineRunner commandLineRunner(StudentDao studentDao) {
        return runner -> {
            createStudent(studentDao);
            // readStudent(studentDao);
            // queryStudentLastName(studentDao);
            // queryStudents(studentDao);
            // updateStudent(studentDao);
            // deleteStudent(studentDao);
            // deleteAllStudent(studentDao);
        };
    }

    /**
     * Deletes all student records from the database and logs the
     * total count of removed rows.
     *
     * @param studentDao the data access object
     */
    private void deleteAllStudent(StudentDao studentDao) {
        System.out.println(
                "Deleting all student information...");
        int rowsDeleted = studentDao.deleteAll();
        System.out.println(
                "Number of deleted rowsL " + rowsDeleted);
    }

    /**
     * Creates a new student entity, persists it to the database,
     * and prints its generated ID.
     *
     * @param studentDao the data access object
     */
    private void createStudent(StudentDao studentDao) {
        System.out.println(
                "Creating student information...");
        Student tempStudent = new Student(
                "Vergel", "Nicanor", "Vergel.Nicanor@example.com");

        System.out.println("Saving student information...");
        studentDao.save(tempStudent);

        System.out.println(
                "Saved! Generated id: " + tempStudent.getId());
    }

    /**
     * Deletes a specific student record by its primary key ID.
     *
     * @param studentDao the data access object
     */
    private void deleteStudent(StudentDao studentDao) {
        int id = 16;
        System.out.println("Delete ID: " + id);

        studentDao.delete(id);
    }

    /**
     * Retrieves a student by ID, updates their first name, and
     * persists the changes.
     *
     * @param studentDao the data access object
     */
    private void updateStudent(StudentDao studentDao) {
        int id = 1;
        System.out.println("Student ID: " + id);
        Student student = studentDao.findById(id);

        System.out.println("Updating student name...");
        student.setFirstName("MIGUELITO");

        studentDao.update(student);

        System.out.println(
                "Updated student information: " + student);
    }

    /**
     * Queries and prints all students matching a given last name.
     *
     * @param studentDao the data access object
     */
    private void queryStudentLastName(StudentDao studentDao) {
        List<Student> students =
                studentDao.findByLastName("Nicanor");

        for (Student s : students) {
            System.out.println(s);
        }
    }

    /**
     * Retrieves and prints all student records from the database.
     *
     * @param studentDao the data access object
     */
    private void queryStudents(StudentDao studentDao) {
        List<Student> students = studentDao.findAll();

        for (Student s : students) {
            System.out.println(s);
        }
    }

    /**
     * Creates and saves a new student, then retrieves and prints
     * the record using its generated primary key.
     *
     * @param studentDao the data access object
     */
    private void readStudent(StudentDao studentDao) {
        System.out.println(
                "Creating student information...");
        Student tempStudent = new Student(
                "Vergel", "Nicanor", "Vergel.Nicanor@example.com");

        System.out.println("Saving student information...");
        studentDao.save(tempStudent);

        int theId = tempStudent.getId();
        System.out.println("Saved! Generated id: " + theId);

        System.out.println(
                "Retrieving student id " + theId + "...");
        Student myStudent = studentDao.findById(theId);

        System.out.println("Found the student " + myStudent);
    }
}