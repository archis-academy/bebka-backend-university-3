package com.archisacademy.dao.impl;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.model.Course;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

        } catch (NumberFormatException e) {
            System.out.println("Geçersiz courseId formatı: " + courseId);
            return;
        }

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
}
