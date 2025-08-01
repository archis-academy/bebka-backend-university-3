package com.archisacademy.service;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Instructor;

import java.util.List;

public class CourseService {
    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Course addCourse(String courseName, long courseNumber, long courseHour,Instructor instructor) {
        Course course = new Course(courseName, courseNumber,courseHour);
        course.setCourseInstructor(instructor);
        return courseDao.addCourse(course);
    }

    public void updateCourse(String courseName, long courseNumber,long  courseHour, Instructor instructor) {
        Course course = new Course(courseName, courseNumber,courseHour);
        course.setCourseInstructor(instructor);
        courseDao.updateCourse(course);
    }

    public void deleteCourseById(long courseId)
    {
        courseDao.deleteCourseById(courseId);
    }


    public List<Course> getAllCourses(){
        List<Course> courses = courseDao.getAllCourses();
        System.out.printf(" %-15s | %-15s | %-15s \n",
                "Kurs Adı", "Kurs No", "Eğitmen Adı");
        System.out.println("-----------------------------------------------------");
        for (Course course : courses) {
            String instructorName = course.getCourseInstructor().getInstructorName();
            System.out.printf(" %-15s | %-15d | %-15s \n",
                    course.getCourseName(),
                    course.getCourseNumber(),
                    instructorName
            );
        }
        return courses;
    }
}
