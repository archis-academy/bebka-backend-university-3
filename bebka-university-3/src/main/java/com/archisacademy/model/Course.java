package com.archisacademy.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @Column(name = "course_number", unique = true, nullable = false)
    private long courseNumber;
    @Column(name = "course_hour")
    private Long courseHour;
    //SINIFLAR ARASI İLİŞKİLER
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor courseInstructor;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_student", // Ara tablo
            joinColumns = @JoinColumn(name = "course_id"), // Bu tablonun id'si
            inverseJoinColumns = @JoinColumn(name = "student_id") // Diğer tablonun id'si
    )
    private List<Student> enrolledStudents;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseStudent> courseStudents;

    public Course() {}

    public Course(String courseName, long courseNumber , long courseHour) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.enrolledStudents = new ArrayList<>();
        this.courseHour=courseHour;
        this.courseStudents = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(long courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Instructor getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

 public void setCourseHour(Long courseHour){
        this.courseHour=courseHour;
 }

    public Long getCourseHour() {
        return courseHour;
    }

    public void addStudent(Student student) {

            if (this.enrolledStudents == null) {
                this.enrolledStudents = new ArrayList<>();
            }
            this.enrolledStudents.add(student);
    }
    public List<CourseStudent> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
    }
}
