package com.archisacademy.model;

import jakarta.persistence.*;

import java.util.Date;

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
    private Double SuccessRate;
    private String feedback;
    private Date startDate;
    private Date endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getAttandance() {
        return attandance;
    }

    public void setAttandance(Double attandance) {
        this.attandance = attandance;
    }

    public Double getSuccessRate() {
        return SuccessRate;
    }

    public void setSuccessRate(Double successRate) {
        SuccessRate = successRate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public CourseReport() {}
    public CourseReport(Course course, Student student, Double successRate, String feedback, Date startDate, Date endDate, Double attandance) {
        this.course = course;
        this.student = student;
        this.SuccessRate = successRate;
        this.feedback = feedback;
        this.startDate = startDate;
        this.endDate = endDate;
        this.attandance = attandance;
    }
}
