package com.archisacademy.dao.impl;

import com.archisacademy.dao.CourseStudentDao;
import com.archisacademy.model.CourseStudent;
import com.archisacademy.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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


    public void updateGrade(Long courseId, Long studentId, double newGrade) {
        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        entityManager.getTransaction().begin();

        String hql = "UPDATE CourseStudent cs SET cs.grade = :grade " +
                "WHERE cs.course.id = :courseId AND cs.student.id = :studentId";

        entityManager.createQuery(hql)
                .setParameter("grade", newGrade)
                .setParameter("courseId", courseId)
                .setParameter("studentId", studentId)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
