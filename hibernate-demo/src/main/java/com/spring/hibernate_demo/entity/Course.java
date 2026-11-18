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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * JPA entity representing a course mapped to the "course" database table.
 */
@Entity
@Table(name = "course")
public class Course {

    /** Primary key identifier for the course. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** Title of the course. */
    @Column(name = "title")
    private String title;

    /** Instructor assigned to teach the course. */
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    /** List of reviews associated with the course. */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> review;

    /** List of students enrolled in the course. */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Course() {
    }

    /**
     * Constructs a new Course with the specified title.
     *
     * @param title the course title
     */
    public Course(String title) {
        this.title = title;
    }

    /**
     * Gets the unique identifier of the course.
     *
     * @return the course ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the course.
     *
     * @param id the course ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the course.
     *
     * @return the course title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     *
     * @param title the course title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the instructor assigned to the course.
     *
     * @return the assigned {@link Instructor}
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor assigned to the course.
     *
     * @param instructor the {@link Instructor} to assign
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Adds a review to this course.
     *
     * @param rev the {@link Review} to add
     */
    public void addReview(Review rev) {
        if (review == null) {
            review = new ArrayList<>();
        }
        review.add(rev);
    }

    /**
     * Gets the list of reviews for this course.
     *
     * @return a {@link List} of {@link Review} entities
     */
    public List<Review> getReview() {
        return review;
    }

    /**
     * Sets the list of reviews for this course.
     *
     * @param review the {@link List} of {@link Review} entities to set
     */
    public void setReview(List<Review> review) {
        this.review = review;
    }

    /**
     * Gets the list of students enrolled in this course.
     *
     * @return a {@link List} of enrolled {@link Student} entities
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the list of students enrolled in this course.
     *
     * @param students the {@link List} of {@link Student} entities to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Adds a student to this course's enrollment list.
     *
     * @param stud the {@link Student} to enroll
     */
    public void addStudent(Student stud) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(stud);
    }

    /**
     * Returns a string representation of the Course entity.
     *
     * @return formatted string containing course ID and title
     */
    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }
}