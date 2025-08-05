package com.archisacademy.dao;

import com.archisacademy.model.Course;
import com.archisacademy.model.CourseStudent;
import com.archisacademy.model.Student;

import java.util.List;

public interface CourseStudentDao {
    void assignStudentToCourse(Course course, Student student, Double grade);
    List<CourseStudent> getByInstructorNumber(long instructorNumber);
}
