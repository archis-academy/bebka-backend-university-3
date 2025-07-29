package com.archisacademy.dao.impl;

import com.archisacademy.dao.StudentDao;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class StudentDaoImpl implements StudentDao {


    @Override
    public void addNewStudent(Student student) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();
            System.out.println("Yeni öğrenci başarıyla eklendi: " + student.getFullName());

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Öğrenci eklenirken bir hata oluştu!");
            e.printStackTrace(); // Hatanın tüm teknik detayını konsola yazdırır
        }
    }
}