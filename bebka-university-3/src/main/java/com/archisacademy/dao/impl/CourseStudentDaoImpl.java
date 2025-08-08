package com.archisacademy.dao.impl;

import com.archisacademy.dao.CourseStudentDao;
import com.archisacademy.model.CourseStudent;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CourseStudentDaoImpl implements CourseStudentDao {
    @Override
    public void assignStudentToCourse(CourseStudent courseStudent) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(courseStudent); // öğrenciyi kursa notuyla birlikte ekliyoruz
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }
}
