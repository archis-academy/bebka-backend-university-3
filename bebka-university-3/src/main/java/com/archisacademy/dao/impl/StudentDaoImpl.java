package com.archisacademy.dao.impl;


import com.archisacademy.dao.StudentDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Student createStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            System.out.println("DAO: Öğrenci veritabanına kaydedildi.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void updateStudent(long studentNumber, String newFullName, String newEmail) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.createQuery(
                            "FROM Student WHERE studentNumber = :studentNumber", Student.class)
                    .setParameter("studentNumber", studentNumber)
                    .uniqueResult();

            if (student != null) {
                student.setName(newFullName);
                student.setEmail(newEmail);


                transaction = session.beginTransaction();

                session.update(student);

                transaction.commit();

                System.out.println("Öğrenci güncellendi.");
            } else {
                System.out.println("Öğrenci bulunamadı!");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}
