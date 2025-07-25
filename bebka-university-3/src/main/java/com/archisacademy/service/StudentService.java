package com.archisacademy.service;

import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.Transaction;

public class StudentService {
    public void addStudent(com.archisacademy.service.Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
             session.save(student);
            transaction.commit();
            System.out.println("Öğrenci başarıyla eklendi: " + student.getFullName());}
        catch (Exception e)
        {if (transaction != null) transaction.rollback();
            e.printStackTrace();
    }
    }
}
