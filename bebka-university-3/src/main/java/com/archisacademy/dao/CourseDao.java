package com.archisacademy.dao;

import com.archisacademy.model.Course;

public interface CourseDao {
    Course getCourseById(Long id);

    Course getCourseByNumber(Long courseNumber);
}
