package com.archisacademy.dao.impl;

import com.archisacademy.dao.CourseStudentDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.CourseStudent;
import com.archisacademy.model.CourseStudentId;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseStudentDaoImpl implements CourseStudentDao {
    @Override
    public void assignStudentToCourse(Course course, Student student, Double grade) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            CourseStudentId id = new CourseStudentId(course.getId(), student.getId());
            CourseStudent cs = session.get(CourseStudent.class, id);

            if (cs == null) {
                // Daha önce ilişki eklenmemiş, yeni oluştur
                CourseStudent newCS = new CourseStudent(course, student, grade);
                session.persist(newCS);  // INSERT
            } else {
                // Zaten varsa sadece notu güncelle
                cs.setGrade(grade);
                session.update(cs);  // UPDATE
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }


    }

    @Override
    public List<CourseStudent> getByInstructorNumber(long instructorNumber) {
        List<CourseStudent> result = new ArrayList<>();
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            result = session.createQuery("""
            SELECT cs FROM CourseStudent cs
            JOIN cs.course c
            WHERE c.courseInstructor.instructorNumber = :instructorNumber
        """, CourseStudent.class)
                    .setParameter("instructorNumber", instructorNumber)
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        return result;
    }
}
