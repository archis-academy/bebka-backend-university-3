
package com.archisacademy.dao;

import com.archisacademy.model.Course;
import com.archisacademy.model.Instructor;

import java.util.List;

public interface InstructorDao {

    void createInstructor(Instructor instructor);
    void deleteInstructor(long instructorNumber);
    void updateInstructor(long instructorNumber, String newInstructorName, String newEmail);
}