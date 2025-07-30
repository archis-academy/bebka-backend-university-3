package com.archisacademy.dao.impl;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public Course addCourse(Course course) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
            System.out.println("Course added successfully.");
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Course existingCourse = session.createQuery(
                            "FROM Course WHERE courseNumber = :courseNumber", Course.class)
                    .setParameter("courseNumber", course.getCourseNumber())
                    .uniqueResult();

            if (existingCourse != null) {
                existingCourse.setCourseName(course.getCourseName());
                existingCourse.setCourseInstructor(course.getCourseInstructor());
                session.update(existingCourse);
                System.out.println("Course updated successfully.");
            } else {
                System.out.println("Course with number " + course.getCourseNumber() + " not found.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourseById(long courseId) {
        Session session = null;
        Transaction transaction = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Course course = session.get(Course.class, courseId);
            if (course != null) {
                session.delete(course);
                System.out.println("Kurs başarıyla silindi. ID: " + courseId);
            } else {
                System.out.println("Kurs bulunamadı. ID: " + courseId);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Kurs silinirken hata oluştu: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void addStudentToCourse(Course course, Student student) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            //güncel hallerini db'den alıyoruz
            course = session.get(Course.class, course.getId());
            student = session.get(Student.class, student.getId());

            //kursa öğrenci atıyoruz
            course.getEnrolledStudents().add(student);
            session.update(course);

            tx.commit();
            System.out.println("Öğrenci kursa başarıyla eklendi.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> getPopularCourses(int topCount) {
        Transaction tx = null;
        List<Course> courses = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            courses = session.createQuery(
                            "SELECT c FROM Course c " +
                                    "LEFT JOIN c.enrolledStudents s " +
                                    "GROUP BY c " +
                                    "ORDER BY COUNT(s) DESC", Course.class)
                    .setMaxResults(topCount)
                    .list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return courses;
    }
}
