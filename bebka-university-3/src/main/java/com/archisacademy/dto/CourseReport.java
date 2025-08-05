package com.archisacademy.dto;

import java.util.List;

// Bu sınıf, raporun sonuçlarını taşımak için kullanılır.
public class CourseReport {

    private long courseId;
    private String courseName;
    private int totalStudents;
    private double successRate; // Başarı oranı (%)
    private List<String> studentFeedbacks; // Öğrenci geri bildirimleri

    // Constructor
    public CourseReport(long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    // Raporun çıktısını daha güzel görmek için toString metodu
    @Override
    public String toString() {
        return "--- Kurs Raporu ---\n" +
                "Kurs ID: " + courseId + "\n" +
                "Kurs Adı: '" + courseName + '\'' + "\n" +
                "Toplam Öğrenci: " + totalStudents + "\n" +
                "Başarı Oranı: %" + String.format("%.2f", successRate) + "\n" +
                "Geri Bildirimler: " + studentFeedbacks + "\n" +
                "--------------------";
    }

    // --- Getter ve Setter Metotları ---
    public long getCourseId() { return courseId; }
    public void setCourseId(long courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getTotalStudents() { return totalStudents; }
    public void setTotalStudents(int totalStudents) { this.totalStudents = totalStudents; }
    public double getSuccessRate() { return successRate; }
    public void setSuccessRate(double successRate) { this.successRate = successRate; }
    public List<String> getStudentFeedbacks() { return studentFeedbacks; }
    public void setStudentFeedbacks(List<String> studentFeedbacks) { this.studentFeedbacks = studentFeedbacks; }
}