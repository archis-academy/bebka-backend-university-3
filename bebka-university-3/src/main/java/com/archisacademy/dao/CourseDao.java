package com.archisacademy.dao;

import com.archisacademy.model.Course;

import java.util.List;

public interface CourseDao {
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(long id);
    List<Course> getAllCourses();
    Course findByCourseId(long courseId);
    void updateCourseContent(long courseId, String newContent);

    }

