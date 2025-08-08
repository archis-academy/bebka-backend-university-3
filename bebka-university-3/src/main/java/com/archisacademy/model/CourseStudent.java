package com.archisacademy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_student",
        uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "student_id"}))
                    // aynı student-course ilişkisi bir kez girilebilir
public class CourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = true)
    private Double grade;

    public CourseStudent() {}

    public CourseStudent(Course course, Student student, Double grade) {
        this.course = course;
        this.student = student;
        this.grade = grade;
    }

    // GETTERS AND SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}