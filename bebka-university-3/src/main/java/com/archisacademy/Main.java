package com.archisacademy.util;

import com.archisacademy.dto.StudentDto;
import com.archisacademy.service.StudentService;
import com.archisacademy.service.impl.StudentServiceImpl;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();

        StudentDto yeniOgrenciDto = new StudentDto();
        yeniOgrenciDto.setStudentNumber("2025999");
        yeniOgrenciDto.setFullName("Test Öğrencisi");
        yeniOgrenciDto.setEmail("test.ogrencisi@example.com");

        String sonucMesaji = studentService.addStudent(yeniOgrenciDto);


        System.out.println(sonucMesaji);
    }
}