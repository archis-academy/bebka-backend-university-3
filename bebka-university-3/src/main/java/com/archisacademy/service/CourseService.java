package com.archisacademy.service;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Instructor;
import com.archisacademy.model.Student;

import java.util.List;

public class CourseService {
    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Course addCourse(String courseName, long courseNumber, Instructor instructor, List<Student> enrolledStudents) {
        Course course = new Course(courseName, courseNumber);
        course.setCourseInstructor(instructor);
        course.setEnrolledStudents(enrolledStudents);
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
    public List<Course> getPopularCourses(int topCount){
        List<Course> popularCourses = courseDao.getPopularCourses(topCount);
        System.out.println("\n--------- | Kayıtlı Öğrenci Sayısına Göre En Popüler Kurslar | ---------");
        System.out.printf(" %-15s | %-15s | %-15s | %-10s\n",
                "Kurs Adı", "Kurs No", "Eğitmen Adı", "Kayıtlı Öğrenci Sayısı");
        System.out.println("------------------------------------------------------------------------------");
        for (Course course : popularCourses) {
            String instructorName = course.getCourseInstructor().getInstructorName();
            int studentCount = course.getEnrolledStudents().size();
            System.out.printf(" %-15s | %-15d | %-15s | %-10d\n",
                    course.getCourseName(),
                    course.getCourseNumber(),
                    instructorName,
                    studentCount
            );
        }
        return popularCourses;
    }

}
