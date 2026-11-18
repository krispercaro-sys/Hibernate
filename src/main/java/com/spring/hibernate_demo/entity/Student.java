package com.spring.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * JPA entity representing a student mapped to the "student" database
 * table.
 */
@Entity
@Table(name = "student")
public class Student {

    /** Primary key identifier for the student. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** First name of the student. */
    @Column(name = "first_name")
    private String firstName;

    /** Last name of the student. */
    @Column(name = "last_name")
    private String lastName;

    /** Email address of the student. */
    @Column(name = "email")
    private String email;

    /** List of courses in which the student is enrolled. */
    @ManyToMany(
            mappedBy = "students",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    private List<Course> courses;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Student() {
    }

    /**
     * Constructs a new Student with personal information.
     *
     * @param firstname the student's first name
     * @param lastname  the student's last name
     * @param email     the student's email address
     */
    public Student(
            String firstname, String lastname, String email) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
    }

    /**
     * Gets the unique identifier of the student.
     *
     * @return the student ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the student.
     *
     * @param id the student ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the student.
     *
     * @return the student's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstName the student's first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the student.
     *
     * @return the student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastName the student's last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the student.
     *
     * @return the student's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the student.
     *
     * @param email the student's email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the list of courses in which the student is enrolled.
     *
     * @return a {@link List} of enrolled {@link Course} entities
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses in which the student is enrolled.
     *
     * @param courses the {@link List} of {@link Course} entities to set
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Convenience method to enroll in a course bidirectionally.
     *
     * @param course the {@link Course} to enroll in
     */
    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.addStudent(this);
    }

    /**
     * Returns a string representation of the Student entity.
     *
     * @return formatted string containing student details
     */
    @Override
    public String toString() {
        return "Student [id=" + id
                + ", firstname=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email + "]";
    }
}