package com.archisacademy.dao;

import com.archisacademy.model.Course;

public interface CourseDao {
    Course getCourseById(Long id);

    Course getCourseByNumber(Long courseNumber);
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(long id);
}
