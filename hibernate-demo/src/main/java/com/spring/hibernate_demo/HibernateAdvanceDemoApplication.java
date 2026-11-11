package com.spring.hibernate_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_demo.dao.AppDao;
import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;

@SpringBootApplication
public class HibernateAdvanceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateAdvanceDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AppDao app) {
        return runner -> {
            // createInstructor(app);
            // findInstructor(app);
            // deleteInstructor(app);
            // findInstructorDetail(app);
            // deleteInstructorDetails(app);
            //createInstructorWithCourses(app);
            findInstructorWithCourses(app);
        };
    }

    private void findInstructorWithCourses(AppDao app){
        int id = 10;
        System.out.println("Find instrucotr id: " + id);

        Instructor ins = app.findInstructorById(id);

        System.out.println("Instructor: " + ins);
        System.out.println("Course: " + ins.getCourses());
    }

    private void createInstructorWithCourses(AppDao app) {

        Instructor ins = new Instructor("Peri", "Winkle", "peri.winkle@example.com");
        InstructorDetail deets = new InstructorDetail("Science", "Sprinkling dust");

        ins.setInstructorDeets(deets);

        Course course1 = new Course("Physics");
        Course course2 = new Course("Biology");

        ins.add(course1);
        ins.add(course2);

        app.saveInstructorWithCourses(ins);

        System.out.println("Course saved: " + ins.getCourses());
    }

    private void deleteInstructorDetails(AppDao app) {
        int id = 6;
        System.out.println("Delete od: " + id);

        app.deleteInstructorDetail(id);
        System.out.println("Deleted successfully!");

    }

    private void findInstructorDetail(AppDao app) {

        int id = 6;
        InstructorDetail inDeets = app.findInstructorDetailById(id);

        System.out.println("Details: " + inDeets);

        System.out.println("Instructor: " + inDeets.getInstructor());
    }

    private void deleteInstructor(AppDao app) {
        int id = 3;
        System.out.println("Delete id: " + id);

        app.deleteInstructorId(id);
        System.out.println("Deleted successsfully!");
    }

    private void findInstructor(AppDao app) {
        int id = 1;
        System.out.println("Find id: " + id);

        Instructor ins = app.findInstructorById(id);

        System.out.println("Instructor:" + ins);
        System.out.println("Details: " + ins.getInstructorDeets());
    }

    private void createInstructor(AppDao app) {

        Instructor ins = new Instructor("Vicky", "Magtanggol", "vicky.mags@example.com");

        InstructorDetail deets = new InstructorDetail("Math", "Solving problems");

        // Now setInstructorDeets works because the setter is defined in Instructor
        ins.setInstructorDeets(deets);

        System.out.println("Saving instructor: " + ins);
        app.save(ins);
        System.out.println("Information saved!");
    }
}