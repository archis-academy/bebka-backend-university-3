
package com.archisacademy.dao;

import com.archisacademy.model.Course;
import com.archisacademy.model.Instructor;
import com.archisacademy.model.Student;

import java.util.List;

public interface InstructorDao {

    Instructor createInstructor(Instructor instructor);
    void deleteInstructor(long instructorNumber);
    void updateInstructor(long instructorNumber, String newInstructorName, String newEmail);
    List<Instructor> getAllInstructors();
    Instructor findByInstructorNumber(long instructorNumber);
    long getTotalStudentCountByInstructorId(long instructorId);
    void getTopRecommendedCourses(long instructorId, int topCount);
    double getAverageGradeByInstructorId(long instructorId);
    Student getInstructorsHighestNote(long instructorNumber);
}