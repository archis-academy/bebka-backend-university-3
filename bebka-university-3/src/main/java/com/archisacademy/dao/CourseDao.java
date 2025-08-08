package com.archisacademy.dao;

import com.archisacademy.model.Course;

import java.util.List;

public interface CourseDao {
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(long id);
    List<Course> getAllCourses();
    Course findByCourseId(long CourseId);
    void updateCourseContent(long courseId, String newContent);
    public class Course {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
