package com.archisacademy.dto;

public class StudentDto {
    private String studentNumber;
    private String fullName;
    private String email;

    // Getter ve Setter Metodları
    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}