package com.archisacademy.dao;

import com.archisacademy.model.Course;
import com.archisacademy.model.Student;

import java.util.List;

public interface StudentDao {
    Student createStudent(Student student);

    void updateStudent(long studentNumber, String newFullName, String newEmail);
    Student getStudentById(long id);
    int getTotalCourseHour(long studentId);
    void assignCourseToStudent(long studentId, long courseId);

}
