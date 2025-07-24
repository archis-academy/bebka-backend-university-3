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
    private long studentNumber;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "savedStudents")
    private List<Course> enrolledCourses;

    public Student(){}
    public Student(long id, String name, long studentNumber, String email, String password){
        this.id=id;
        this.name=name;
        this.studentNumber=studentNumber;
        this.email=email;
        this.password=password;
        this.enrolledCourses=new ArrayList<>();
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


}
