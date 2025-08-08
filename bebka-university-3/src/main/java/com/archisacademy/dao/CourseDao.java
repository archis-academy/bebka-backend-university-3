package com.archisacademy.dao;

import com.archisacademy.model.Course;
import com.archisacademy.model.Student;

import java.util.List;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(long id);
    List<Course> getAllCourses();
    List<Course> getPopularCourses(int topCount);
    Course getCourseById(Long id);
    Course getCourseByNumber(Long courseNumber);
    List<Course> getCoursesByStudentId(long id);
    List<Course> searchCoursesByName(String courseName, Map<String, String> filters);
    Course findMostPopularCourse();
}
