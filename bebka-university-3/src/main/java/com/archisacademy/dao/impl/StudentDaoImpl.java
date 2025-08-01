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

    public Student getStudentById(long id) {
        Student student = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            student = session.get(Student.class, id);

            if (student != null) {
                System.out.println("Öğrenci bulundu: " + student.getName());
            } else {
                System.out.println("ID ile öğrenci bulunamadı.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public int getTotalCourseHour(long studentId) {
        int totalCourseHour = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.get(Student.class, studentId);

            if (student != null) {
                List<Course> courses = student.getEnrolledCourses();
                for (Course course : courses) {
                    totalCourseHour += course.getCourseHour();
                }
            } else {
                System.out.println("Öğrenci bulunamadı. ID: " + studentId);
            }
        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }

        return totalCourseHour;
    }

    public void assignCourseToStudent(long studentId, long courseId) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Student student = session.get(Student.class, studentId);
            Course course = session.get(Course.class, courseId);

            if (student != null && course != null) {
                student.addCourse(course);
                course.addStudent(student);

                session.update(student);
                session.update(course);

                transaction.commit();
                System.out.println("Kurs öğrenciye başarıyla atandı.");
            } else {
                System.out.println("Student veya Course bulunamadı.");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


}
