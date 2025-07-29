package com.archisacademy.service.impl;

import com.archisacademy.dao.StudentDao;
import com.archisacademy.dao.impl.StudentDaoImpl;
import com.archisacademy.dto.StudentDto;
import com.archisacademy.model.Student;
import com.archisacademy.service.StudentService;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public String addStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setStudentNumber(studentDto.getStudentNumber());
        student.setFullName(studentDto.getFullName());
        student.setEmail(studentDto.getEmail());

        try {
            studentDao.addNewStudent(student);
            return "Başarılı: " + student.getFullName() + " isimli öğrenci eklendi.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Hata: Öğrenci eklenirken bir sorun oluştu.";
        }
    }
}