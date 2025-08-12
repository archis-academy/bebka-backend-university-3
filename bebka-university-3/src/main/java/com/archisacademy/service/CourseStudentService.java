package com.archisacademy.service;

import com.archisacademy.dao.CourseStudentDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.CourseStudent;
import com.archisacademy.model.Student;

public class CourseStudentService {
    private final CourseStudentDao  courseStudentDao;

    public CourseStudentService(CourseStudentDao courseStudentDao) {
        this.courseStudentDao = courseStudentDao;
    }

    public void assignStudentToCourse(Course course, Student student, Double grade) {
        CourseStudent courseStudent = new CourseStudent(course, student, grade);
        courseStudentDao.assignStudentToCourse(courseStudent);
    }

    public void updateGrade(Long courseId, Long studentId, double newGrade){
        courseStudentDao.updateGrade(courseId,studentId,newGrade);
    }

}
