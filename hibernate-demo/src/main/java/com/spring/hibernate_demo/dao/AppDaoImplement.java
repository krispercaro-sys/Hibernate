package com.spring.hibernate_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_demo.entity.Course;
import com.spring.hibernate_demo.entity.Instructor;
import com.spring.hibernate_demo.entity.InstructorDetail;
import com.spring.hibernate_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * Implementation of AppDao handling persistence operations for
 * Instructors, Courses, and Students.
 */
@Repository
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    /**
     * Constructs an AppDaoImplement with the given EntityManager.
     *
     * @param em the JPA EntityManager
     */
    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * Deletes an Instructor by their ID if found.
     *
     * @param theId the ID of the instructor to delete
     */
    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor in = em.find(Instructor.class, theId);
        if (in != null) {
            em.remove(in);
        }
    }

    /**
     * Deletes a Course by its ID.
     *
     * @param theId the ID of the course to delete
     */
    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = em.find(Course.class, theId);
        em.remove(course);
    }

    /**
     * Updates an existing Instructor entity.
     *
     * @param in the instructor entity to update
     */
    @Override
    @Transactional
    public void update(Instructor in) {
        em.merge(in);
    }

    /**
     * Persists a new Instructor entity.
     *
     * @param theInstructor the instructor to save
     */
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    /**
     * Deletes an Instructor directly by ID.
     *
     * @param theId the ID of the instructor
     */
    @Override
    @Transactional
    public void deleteInstructorId(int theId) {
        Instructor in = em.find(Instructor.class, theId);
        em.remove(in);
    }

    /**
     * Saves an Instructor along with their associated courses.
     *
     * @param theInstructor the instructor to persist
     */
    @Override
    @Transactional
    public void saveInstructorWithCourses(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    /**
     * Deletes an InstructorDetail and unlinks it from its Instructor.
     *
     * @param theId the ID of the instructor detail
     */
    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        InstructorDetail deets =
                em.find(InstructorDetail.class, theId);
        deets.getInstructor().setInstructorDeets(null);
        em.remove(deets);
    }

    /**
     * Finds an Instructor along with courses and details via join fetch.
     *
     * @param theId the instructor ID
     * @return the fetched Instructor
     */
    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = em.createQuery(
                "SELECT i FROM Instructor i "
                        + "JOIN FETCH i.courses c "
                        + "JOIN FETCH i.instructorDeets d "
                        + "WHERE i.id = :data",
                Instructor.class);
        query.setParameter("data", theId);
        return query.getSingleResult();
    }

    /**
     * Finds an Instructor by ID.
     *
     * @param theId the instructor ID
     * @return the found Instructor or null
     */
    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    /**
     * Finds an InstructorDetail by ID.
     *
     * @param theId the instructor detail ID
     * @return the found InstructorDetail or null
     */
    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    /**
     * Retrieves all courses taught by a specific instructor.
     *
     * @param theId the instructor ID
     * @return list of associated Courses
     */
    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = em.createQuery(
                "FROM Course WHERE instructor.id = :data",
                Course.class);
        query.setParameter("data", theId);
        return query.getResultList();
    }

    /**
     * Persists a new Course entity.
     *
     * @param theCourse the course to save
     */
    @Override
    @Transactional
    public void save(Course theCourse) {
        em.persist(theCourse);
    }

    /**
     * Finds a Course and eagerly fetches its reviews.
     *
     * @param theId the course ID
     * @return the Course with reviews
     */
    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                        + "JOIN FETCH c.review "
                        + "WHERE c.id = :data",
                Course.class);
        query.setParameter("data", theId);
        return query.getSingleResult();
    }

    /**
     * Finds a Course and eagerly fetches its enrolled students.
     *
     * @param theId the course ID
     * @return the Course with students
     */
    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                        + "JOIN FETCH c.students "
                        + "WHERE c.id = :data",
                Course.class);
        query.setParameter("data", theId);
        return query.getSingleResult();
    }

    /**
     * Finds a Student and eagerly fetches their enrolled courses.
     *
     * @param theId the student ID
     * @return the Student with courses
     */
    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s "
                        + "JOIN FETCH s.courses "
                        + "WHERE s.id = :data",
                Student.class);
        query.setParameter("data", theId);
        return query.getSingleResult();
    }

    /**
     * Updates an existing Student entity.
     *
     * @param student the student to update
     */
    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    /**
     * Deletes a Student by ID after removing course associations.
     *
     * @param theId the student ID
     */
    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student student = em.find(Student.class, theId);
        if (student != null) {
            List<Course> courses = student.getCourses();
            for (Course c : courses) {
                c.getStudents().remove(student);
            }
            em.remove(student);
        }
    }
}