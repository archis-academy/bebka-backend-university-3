package com.archisacademy.model;

import java.util.List;

public class CourseReport {

    private long courseId;
    private String courseName;
    private int totalStudents;
    private List<String> studentNames;

    @Override
    public String toString() {
        return "--- Kurs Raporu ---\n" +
                "Kurs ID: " + courseId + "\n" +
                "Kurs Adı: '" + courseName + '\'' + "\n" +
                "Toplam Öğrenci: " + totalStudents + "\n" +
                "Kayıtlı Öğrenciler: " + studentNames + "\n" +
                "--------------------";
    }
    public long getCourseId() { return courseId; }
    public void setCourseId(long courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getTotalStudents() { return totalStudents; }
    public void setTotalStudents(int totalStudents) { this.totalStudents = totalStudents; }
    public List<String> getStudentNames() { return studentNames; }
    public void setStudentNames(List<String> studentNames) { this.studentNames = studentNames; }
}