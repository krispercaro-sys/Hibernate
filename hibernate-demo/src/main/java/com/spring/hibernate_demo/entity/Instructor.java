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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * JPA entity representing an instructor mapped to the "instructor"
 * database table.
 */
@Entity
@Table(name = "instructor")
public class Instructor {

    /** Primary key identifier for the instructor. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** First name of the instructor. */
    @Column(name = "first_name")
    private String firstName;

    /** Last name of the instructor. */
    @Column(name = "last_name")
    private String lastName;

    /** Email address of the instructor. */
    @Column(name = "email")
    private String email;

    /** Supplemental profile details for the instructor. */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDeets;

    /** List of courses taught by the instructor. */
    @OneToMany(
            mappedBy = "instructor",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            })
    private List<Course> courses;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Instructor() {
    }

    /**
     * Constructs a new Instructor with personal details.
     *
     * @param firstName the instructor's first name
     * @param lastName  the instructor's last name
     * @param email     the instructor's email address
     */
    public Instructor(
            String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Gets the unique identifier of the instructor.
     *
     * @return the instructor ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the instructor.
     *
     * @param id the instructor ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the instructor.
     *
     * @return the instructor's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the instructor.
     *
     * @param firstName the instructor's first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the instructor.
     *
     * @return the instructor's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the instructor.
     *
     * @param lastName the instructor's last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the instructor.
     *
     * @return the instructor's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the instructor.
     *
     * @param email the instructor's email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the supplemental details of the instructor.
     *
     * @return the {@link InstructorDetail} entity
     */
    public InstructorDetail getInstructorDeets() {
        return instructorDeets;
    }

    /**
     * Sets the supplemental details of the instructor.
     *
     * @param instructorDeets the {@link InstructorDetail} entity to set
     */
    public void setInstructorDeets(
            InstructorDetail instructorDeets) {
        this.instructorDeets = instructorDeets;
    }

    /**
     * Gets the list of courses taught by the instructor.
     *
     * @return a {@link List} of {@link Course} entities
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses taught by the instructor.
     *
     * @param courses the {@link List} of {@link Course} entities to set
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Convenience method to link a course bidirectionally.
     *
     * @param tempCourse the {@link Course} to add
     */
    public void add(Course tempCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(tempCourse);
        tempCourse.setInstructor(this);
    }

    /**
     * Returns a string representation of the Instructor entity.
     *
     * @return formatted string containing instructor details
     */
    @Override
    public String toString() {
        return "Instructor [id=" + id
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email
                + ", instructorDeets=" + instructorDeets + "]";
    }
}