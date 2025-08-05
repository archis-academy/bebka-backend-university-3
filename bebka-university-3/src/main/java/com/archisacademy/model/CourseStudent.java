package com.archisacademy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_student")
public class CourseStudent {

    @EmbeddedId
    private CourseStudentId id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = true)
    private Double grade;

    public CourseStudent(){}

    public CourseStudent(Course course, Student student, Double grade) {
        this.course = course;
        this.student = student;
        this.grade = grade;
        this.id = new CourseStudentId(course.getId(), student.getId());
    }

    public CourseStudentId getId() {
        return id;
    }

    public void setId(CourseStudentId id) {
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
