package com.archisacademy.dao;

import com.archisacademy.model.CourseStudent;

public interface CourseStudentDao {
    void assignStudentToCourse(CourseStudent courseStudent);
    void updateGrade(Long courseId, Long studentId, double newGrade);
}
