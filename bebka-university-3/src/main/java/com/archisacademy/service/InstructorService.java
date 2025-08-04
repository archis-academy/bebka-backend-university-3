package com.archisacademy.service;

import com.archisacademy.dao.InstructorDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Instructor;

import java.util.List;

public class InstructorService {

    private final InstructorDao instructorDao;

    public InstructorService(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    public Instructor createInstructor(String instructorName,long instructorNumber,String email,String password) {
            Instructor instructor=new Instructor(instructorName,instructorNumber,email,password);
            instructorDao.createInstructor(instructor);
            return instructor;
    }

    public void deleteInstructor(long instructorNumber) {
        instructorDao.deleteInstructor(instructorNumber);
    }

    public void updateInstructor(long instructorNumber, String newInstructorName, String newEmail) {
        instructorDao.updateInstructor(instructorNumber, newInstructorName, newEmail);
    }

    public List<Instructor> getAllInstructors()
    {
        return instructorDao.getAllInstructors();
    }
    public void findByInstructorNumber (long instructorNumber){
        Instructor instructor= instructorDao.findByInstructorNumber( instructorNumber);
        if(instructor== null){
            System.out.println("Eğitmen bulunamadı");

        }
        System.out.printf("%-5s | %-20s | %-25s | %-15s%n", "ID", "Adı", "Email", "Numarası");

        System.out.printf("%-5d | %-20s | %-25s | %-15d%n",
                instructor.getId(),
                instructor.getInstructorName(),
                instructor.getEmail(),
                instructor.getInstructorNumber());
    }

    public List<Course> getTopRecommendedCourses(long instructorId, int topCount) {
        return instructorDao.getTopRecommendedCourses(instructorId, topCount);
    }
}
