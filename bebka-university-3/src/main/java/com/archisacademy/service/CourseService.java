package com.archisacademy.service;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.model.Course;

import java.util.List;

public class CourseService {
    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Course addCourse(String courseName, long courseNumber) {
        if(courseName == null || courseNumber <= 0){
            return null;
        }
        Course course = new Course(courseName, courseNumber);
        return courseDao.addCourse(course);
    }

    public List<Course> getAllCourses(){
        return courseDao.getAllCourses();
    }
}
