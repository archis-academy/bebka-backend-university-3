package com.archisacademy.service;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.dao.impl.CourseDaoImpl;
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

    public void deleteCourseById(long courseId)
    {
        courseDao.deleteCourseById(courseId);
    }

    public CourseService (){
        this.courseDao=new CourseDaoImpl();
    }
    public Course getCourseById(Long id){
        if (id==null || id<=0){
            throw new IllegalArgumentException("GEÇERSİZ KURS ID!!");
        }
        return courseDao.getCourseById(id);
    }
    public Course getCourseByNumber(Long courseNumber){
        if (courseNumber==null || courseNumber<=0){
            throw new IllegalArgumentException("GEÇERSİZ KURS NUMARASI!!");
        }
        return courseDao.getCourseByNumber(courseNumber);
    }
}
