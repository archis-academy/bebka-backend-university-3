package com.archisacademy.service;

import com.archisacademy.dao.StudentDao;
import com.archisacademy.model.Student;

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
}
