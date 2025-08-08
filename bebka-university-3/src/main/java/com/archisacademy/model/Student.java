package com.archisacademy.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name= "student_name")
    private String name;
    @Column(name = "student_number",nullable = false, unique = true)
    private long studentNumber;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "enrolledStudents")
    private List<Course> enrolledCourses;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseStudent> courseStudents;

    public Student(){}
    public Student( String name, long studentNumber, String email, String password){
        this.name=name;
        this.studentNumber=studentNumber;
        this.email=email;
        this.password=password;
        this.enrolledCourses=new ArrayList<>();
        this.courseStudents=new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public List<CourseStudent> getCourseStudents() {
        return courseStudents;
    }
    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public void addCourse(Course course) {
        if (this.enrolledCourses == null) {
            this.enrolledCourses = new ArrayList<>();
        }
        if (!this.enrolledCourses.contains(course)) {
            this.enrolledCourses.add(course);
        }
    }

    public void setCourseStudents(List<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
    }
}
