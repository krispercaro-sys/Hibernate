package com.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * JPA entity representing a user review mapped to the "review"
 * database table.
 */
@Entity
@Table(name = "review")
public class Review {

    /** Primary key identifier for the review. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** Text content of the review comment. */
    @Column(name = "comments")
    private String comments;

    /**
     * Default no-argument constructor required by JPA.
     */
    public Review() {
    }

    /**
     * Constructs a new Review with the specified comment text.
     *
     * @param comments the review comment text
     */
    public Review(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the unique identifier of the review.
     *
     * @return the review ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the review.
     *
     * @param id the review ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the comment text of the review.
     *
     * @return the review comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comment text of the review.
     *
     * @param comments the review comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Returns a string representation of the Review entity.
     *
     * @return formatted string containing review ID and comment
     */
    @Override
    public String toString() {
        return "Review [id=" + id + ", comment=" + comments + "]";
    }

}