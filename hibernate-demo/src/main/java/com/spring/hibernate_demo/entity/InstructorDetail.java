package com.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * JPA entity representing supplemental instructor details mapped to the
 * "instructor_detail" database table.
 */
@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    /** Primary key identifier for the instructor detail record. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** Academic subject taught by the instructor. */
    @Column(name = "subject")
    private String subject;

    /** Personal hobby of the instructor. */
    @Column(name = "hobby")
    private String hobby;

    /** Associated instructor owning these details. */
    @OneToOne(mappedBy = "instructorDeets", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Instructor instructor;

    /**
     * Default no-argument constructor required by JPA.
     */
    public InstructorDetail() {
    }

    /**
     * Constructs a new InstructorDetail with subject and hobby.
     *
     * @param subject the instructor's teaching subject
     * @param hobby   the instructor's hobby
     */
    public InstructorDetail(String subject, String hobby) {
        this.subject = subject;
        this.hobby = hobby;
    }

    /**
     * Gets the unique identifier of the instructor detail record.
     *
     * @return the instructor detail ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the instructor detail record.
     *
     * @param id the instructor detail ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the instructor's teaching subject.
     *
     * @return the teaching subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the instructor's teaching subject.
     *
     * @param subject the teaching subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the instructor's hobby.
     *
     * @return the instructor's hobby
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * Sets the instructor's hobby.
     *
     * @param hobby the instructor's hobby to set
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * Gets the instructor associated with these details.
     *
     * @return the associated {@link Instructor}
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor associated with these details.
     *
     * @param instructor the {@link Instructor} to associate
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Returns a string representation of the InstructorDetail entity.
     *
     * @return formatted string containing ID, subject, and hobby
     */
    @Override
    public String toString() {
        return "InstructorDetail [id=" + id
                + ", subject=" + subject
                + ", hobby=" + hobby + "]";
    }
}