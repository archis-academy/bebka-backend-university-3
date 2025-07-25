package com.archisacademy.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "instructor_name",nullable = false)
    private String instructorName;
    @Column(name = "instructor_number",unique = true)
    private long instructorNumber;
    private String email;
    private String password;

    @OneToMany(mappedBy = "courseInstructor", cascade =CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Course> taughtCourses;

    public Instructor() {}

    public Instructor(String instructorName, long instructorNumber, String email, String password) {
        this.instructorName = instructorName;
        this.instructorNumber = instructorNumber;
        this.email = email;
        this.password = password;
        this.taughtCourses = new ArrayList<>();
        }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public long getInstructorNumber() {
        return instructorNumber;
    }

    public void setInstructorNumber(long instructorNumber) {
        this.instructorNumber = instructorNumber;
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
}
