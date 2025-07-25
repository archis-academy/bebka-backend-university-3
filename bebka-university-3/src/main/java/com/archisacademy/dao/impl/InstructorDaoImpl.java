package com.archisacademy.dao.impl;

import com.archisacademy.dao.InstructorDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Instructor;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InstructorDaoImpl implements InstructorDao {

    @Override
    public void addInstructor(long instructorNumber, String instructorName, String email, List<Course> taughtCourses) {
        // 1. Instructor nesnesi oluştur
        Instructor instructor = new Instructor();
        instructor.setInstructorNumber(instructorNumber);
        instructor.setInstructorName(instructorName);
        instructor.setEmail(email);
        instructor.setTaughtCourses(taughtCourses);

        // 2. Hibernate Session ve Transaction başlat
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // 3. Instructor nesnesini kaydet
            session.save(instructor);

            // 4. Commit
            transaction.commit();

            System.out.println("Instructor başarıyla kaydedildi!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
