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
 * Implementation of {@link AppDao} handling persistence operations for
 * Instructors, Courses, and Students.
 */
@Repository
public class AppDaoImplement implements AppDao {

    /** JPA entity manager used for database operations. */
    private EntityManager em;

    /**
     * Constructs an AppDaoImplement with the given EntityManager.
     *
     * @param em the JPA {@link EntityManager}
     */
    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = em.find(Course.class, theId);
        em.remove(course);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(Instructor in) {
        em.merge(in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteInstructorAndCoursesById(int theId) {
        Instructor in = em.find(Instructor.class, theId);
        em.remove(in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveInstructorWithCourses(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(Course theCourse) {
        em.persist(theCourse);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    /**
     * {@inheritDoc}
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