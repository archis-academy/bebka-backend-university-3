package com.archisacademy.dao;

import com.archisacademy.model.Course;
import com.archisacademy.model.Student;

import java.util.List;

public interface StudentDao {
    Student createStudent(Student student);

    List<Course> getCoursesByStudentId(long id);
}
