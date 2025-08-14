package com.archisacademy.dao;

import com.archisacademy.model.Course;
import com.archisacademy.model.Student;

import java.util.List;

public interface StudentDao {
    Student createStudent(Student student);
    void updateStudent(long studentNumber, String newFullName, String newEmail);
    Student getStudentById(long id);
    int getTotalCourseHour(long studentId);
    List<Long> getCoursesByStudentId(long studentId);
    Student getStudentByNumber(long studentNumber);
    List<Student> getAllStudents();
    void getRecommendedCoursesForStudent(long studentId);
    void deleteStudentByNumber(long studentNumber);
    String getLetterGrade(long studentId, long courseId);
}

