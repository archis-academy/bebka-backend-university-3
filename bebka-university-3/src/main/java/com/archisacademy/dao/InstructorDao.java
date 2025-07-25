
package com.archisacademy.dao;

import com.archisacademy.model.Course;
import java.util.List;

public interface InstructorDao {

    void addInstructor(long instructorNumber, String instructorName, String email, List<Course> taughtCourses);
}