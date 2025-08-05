package com.archisacademy.service;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.dao.impl.CourseDaoImpl;
import com.archisacademy.dto.CourseReport;
import com.archisacademy.model.Course;
import com.archisacademy.model.Student;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CourseService {

    private final CourseDao courseDao;

    public CourseService() {
        this.courseDao = new CourseDaoImpl();
    }

    public CourseReport generateSpecialCourseReport(long courseId) {

        Course course = courseDao.findByIdWithStudents(courseId)
                .orElseThrow(() -> new RuntimeException("HATA: " + courseId + " ID'li kurs bulunamadı!"));

        List<Student> students = course.getEnrolledStudents();

        if (students == null || students.isEmpty()) {
            return createEmptyReportForCourse(course);
        }

        long successfulStudentCount = students.stream()
                .filter(Student::isSuccessful)
                .count();

        List<String> feedbacks = students.stream()
                .map(Student::getFeedback)
                .filter(fb -> fb != null && !fb.trim().isEmpty())
                .collect(Collectors.toList());

        CourseReport report = new CourseReport(course.getId(), course.getCourseName());
        report.setTotalStudents(students.size());

        double successRate = (double) successfulStudentCount / students.size() * 100.0;
        report.setSuccessRate(successRate);

        report.setStudentFeedbacks(feedbacks);

        return report;
    }

    private CourseReport createEmptyReportForCourse(Course course) {
        CourseReport report = new CourseReport(course.getId(), course.getCourseName());
        report.setTotalStudents(0);
        report.setSuccessRate(0.0);
        report.setStudentFeedbacks(Collections.emptyList());
        return report;
    }
}