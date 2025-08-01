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

    public Student getStudentByNumber(long studentNumber) {
        Student student = studentDao.getStudentByNumber(studentNumber);

        if (student != null) {
            System.out.println("Adı: " + student.getName());
            System.out.println("Email: " + student.getEmail());
        } else {
            System.out.println("Öğrenci bulunamadı.");
        }

        return student;
    }

    public void printRecommendedCourses(long studentId) {
        studentDao.getRecommendedCoursesForStudent(studentId);
    }

    public void deleteStudentByNumber(long studentNumber) { studentDao.deleteStudentByNumber(studentNumber);}

    public Student getStudentById(long id)
    {
        return studentDao.getStudentById(id);
    }

    public int getTotalCourseHour(long studentId)
    {
        return  studentDao.getTotalCourseHour(studentId);
    }

    public void assignCourseToStudent(long studentId,long courseId){
        studentDao.assignCourseToStudent(studentId,courseId);
    }


}



