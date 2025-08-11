package com.archisacademy.service;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.dao.StudentDao;
import com.archisacademy.dao.impl.StudentDaoImpl;
import com.archisacademy.model.Course;
import com.archisacademy.model.CourseReport;
import com.archisacademy.model.Instructor;
import com.archisacademy.model.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }


    public Course addCourse(String courseName, long courseNumber, long courseHour,List<Student> enrolledStudents,Instructor instructor) {
        Course course = new Course(courseName, courseNumber,courseHour);
        course.setCourseInstructor(instructor);
        course.setEnrolledStudents(enrolledStudents);
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
    public Course getCourseById(Long id){
        if (id==null || id<=0){
            throw new IllegalArgumentException("GEÇERSİZ KURS ID!!");
        }
        return courseDao.getCourseById(id);
    }
    public Course getCourseByNumber(Long courseNumber){
        if (courseNumber==null || courseNumber<=0){
            throw new IllegalArgumentException("GEÇERSİZ KURS NUMARASI!!");
        }
        return courseDao.getCourseByNumber(courseNumber);
    }
    public List <Course> getCoursesByStudentId(long id){
        List <Course> enrolledCourses=courseDao.getCoursesByStudentId(id);
        if (enrolledCourses != null && !enrolledCourses.isEmpty()){
            System.out.println("Öğrenciye ait kurslar:");
        }else {
            System.out.println("Öğrenciye ait kurs bulunamadı!!");
        }
        return enrolledCourses;
    }

    public List<Course> searchCoursesByName(String courses,String instructorid)
    {
        Map<String, String> filters = new HashMap<>();
        filters.put("instructorId", instructorid);

        List<Course> courseslist = courseDao.searchCoursesByName(courses, filters);

        return courseslist;
    }
    public Course findMostPopularCourse() {
        System.out.println("En popüler kurs aranıyor...");
        Course popularCourse = courseDao.findMostPopularCourse();

        if (popularCourse != null) {
            System.out.println("En popüler kurs bulundu: " + popularCourse.getCourseName());
        } else {
            System.out.println("Sistemde görüntülenecek kurs bulunamadı.");
        }

        return popularCourse;
    }

    public List<CourseReport>  courseReport(long courseId) {
        //System.out.printf("Öğrenci ID: %d | Kurs ID: %d için kurs raporu oluşturuluyor...\n", studentId, courseId);
        List<CourseReport> reports = courseDao.courseReport(courseId);
        //StudentDao studentDao = new StudentDaoImpl();

        for (CourseReport report : reports) {
            System.out.println(report.getCourse().getCourseName() + " - " +
                    report.getStudent().getName() +
                    report.getSuccessRate()+
                    " | Feedback: " + report.getFeedback());
            return courseDao.courseReport(courseId);
        }
        return reports;

    }

    public List<CourseReport> attendanceReport(long studentId, Date startDate, Date endDate) {
        //System.out.printf("Öğrenci ID: %d | Başlangıç Tarihi: %s | Bitiş Tarihi: %s için katılım raporu oluşturuluyor...\n", studentId, startDate, endDate);
        List<CourseReport> reports = courseDao.attendanceReport(studentId, startDate, endDate);

        for (CourseReport report : reports) {
            System.out.println(report.getCourse().getCourseName() + " - " +
                    report.getStudent().getName() +
                    report.getAttandance()+
                    " | Başlangıç Tarihi: " + report.getStartDate() +
                    " | Bitiş Tarihi: " + report.getEndDate());
        }
        return reports;
    }
}

