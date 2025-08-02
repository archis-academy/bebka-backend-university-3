package com.archisacademy.service;

import com.archisacademy.dao.StudentDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        studentDao.updateStudent(studentNumber, newFullName, newEmail);
    }
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

}



