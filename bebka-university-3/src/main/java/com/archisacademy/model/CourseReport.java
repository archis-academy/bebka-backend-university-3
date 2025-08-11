package com.archisacademy.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "course_reports")
public class CourseReport {
    @Id
    @GeneratedValue // Auto-generated ID for each report
    private long Id;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    private Double successRate;
    private String feedback;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double attandance;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getAttandance() {
        return attandance;
    }

    public void setAttandance(Double attandance) {
        this.attandance = attandance;
    }

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public CourseReport() {}
    public CourseReport(Course course, Student student, Double successRate, String feedback, LocalDate startDate, LocalDate endDate, Double attandance) {
        this.course = course;
        this.student = student;
        this.successRate = successRate;
        this.feedback = feedback;
        this.startDate = startDate;
        this.endDate = endDate;
        this.attandance = attandance;
    }
}
