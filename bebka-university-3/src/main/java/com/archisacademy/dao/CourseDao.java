package com.archisacademy.dao;

import com.archisacademy.model.Course;
import com.archisacademy.model.Student;

import java.util.List;

public interface CourseDao {
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(long id);
    List<Course> getPopularCourses(int topCount);
}
