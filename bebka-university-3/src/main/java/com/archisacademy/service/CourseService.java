package com.archisacademy.service;
import com.archisacademy.dao.CourseDao;
import com.archisacademy.dao.impl.CourseDaoImpl;
import com.archisacademy.model.Course;

public class CourseService {
    private final CourseDao courseDao;
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
