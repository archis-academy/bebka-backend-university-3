package com.archisacademy.dao;

import com.archisacademy.model.Course;

import java.util.List;

public interface CourseDao {
    Course addCourse(Course course);
    List<Course> getAllCourses();
}
