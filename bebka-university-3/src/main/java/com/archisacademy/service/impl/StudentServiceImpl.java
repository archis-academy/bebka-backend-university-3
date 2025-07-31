package com.archisacademy.service.impl;

import com.archisacademy.dao.StudentDao;
import com.archisacademy.dao.impl.StudentDaoImpl;
import com.archisacademy.model.Student;
import com.archisacademy.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {


    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> getAllStudents() {
        System.out.println("Service katmanı: Öğrenciler DAO'dan isteniyor...");

        List<Student> studentList = studentDao.getAllStudents();

        System.out.println("Service katmanı: Öğrenciler başarıyla alınd.");
        return studentList;
    }
}