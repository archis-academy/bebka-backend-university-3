package com.archisacademy.service;

import com.archisacademy.dao.CourseStudentDao;
import com.archisacademy.dao.impl.CourseStudentDaoImpl;
import com.archisacademy.model.Course;
import com.archisacademy.model.CourseStudent;
import com.archisacademy.model.Student;

public class CourseStudentService {
    private final CourseStudentDao  courseStudentDao;

    public CourseStudentService(CourseStudentDao courseStudentDao) {
        this.courseStudentDao = courseStudentDao;
    }

    public void assignStudentToCourse(Course course, Student student, Double grade) {
        //CourseStudent courseStudent = new CourseStudent(course, student, grade);
        courseStudentDao.assignStudentToCourse(course, student, grade);
    }
}
