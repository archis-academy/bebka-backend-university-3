package com.archisacademy.service;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Instructor;

public class CourseService {
    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Course addCourse(String courseName, long courseNumber, Instructor instructor) {
        Course course = new Course(courseName, courseNumber);
        course.setCourseInstructor(instructor);
        return courseDao.addCourse(course);
    }

    public void updateCourse(String courseName, long courseNumber, Instructor instructor) {
        Course course = new Course(courseName, courseNumber);
        course.setCourseInstructor(instructor);
        courseDao.updateCourse(course);
    }

    public void deleteCourseById(String courseId)
    {
        courseDao.deleteCourseById(courseId);
    }

}
