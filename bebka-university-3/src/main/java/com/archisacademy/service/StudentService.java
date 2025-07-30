package com.archisacademy.service;

import com.archisacademy.dao.StudentDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Student createStudent(String name,long studentNumber,String email,
                                 String password) {
        Student student = new Student();
        student.setName(name);
        student.setStudentNumber(studentNumber);
        student.setEmail(email);
        student.setPassword(password);
        studentDao.createStudent(student);
        return student;
    }

    public void updateStudent(long studentNumber, String newFullName, String newEmail, List<Course> newEnrolledCourses) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.createQuery(
                            "FROM Student WHERE studentNumber = :studentNumber", Student.class)
                    .setParameter("studentNumber", studentNumber)
                    .uniqueResult();

            if (student != null) {
                student.setName(newFullName);
                student.setEmail(newEmail);
                student.getEnrolledCourses().clear();
                student.getEnrolledCourses().addAll(newEnrolledCourses);

                studentDao.updateStudent(student);
                System.out.println("Öğrenci güncellendi.");
            } else {
                System.out.println("Öğrenci bulunamadı!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



