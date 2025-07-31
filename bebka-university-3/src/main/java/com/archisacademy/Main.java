package com.archisacademy.util;

import com.archisacademy.model.Student;
import com.archisacademy.service.StudentService;
import com.archisacademy.service.impl.StudentServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Uygulama Başlatıldı!");

        StudentService studentService = new StudentServiceImpl();
        List<Student> allStudents = studentService.getAllStudents();

        if (allStudents == null || allStudents.isEmpty()) {
            System.out.println("Sistemde kayıtlı öğrenci bulunamadı.");
        } else {
            System.out.println("----- Sistemdeki Öğrenciler -----");
            for (Student student : allStudents) {
                // DOĞRU METOTLAR KULLANILARAK GÜNCELLENDİ
                System.out.println("Adı: " + student.getName() + ", Numara: " + student.getStudentNumber());
            }
            System.out.println("---------------------------------");
        }
        System.out.println("Uygulama Tamamlandı.");
    }
}