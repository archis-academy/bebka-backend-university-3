package com.archisacademy.dao.impl;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.model.Course;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CourseDaoImpl implements CourseDao {
    @Override
    public Course getCourseById(Long id) {
        Transaction transaction = null;
        Course course = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            course = session.get(Course.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public Course getCourseByNumber(Long courseNumber) {
        Transaction transaction = null;
        Course course = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Course> query = session.createQuery("FROM Course WHERE courseNumber = :courseNumber", Course.class);
            query.setParameter("courseNumber", courseNumber);
            course = query.uniqueResultOptional().orElse(null);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return course;

    }
}


