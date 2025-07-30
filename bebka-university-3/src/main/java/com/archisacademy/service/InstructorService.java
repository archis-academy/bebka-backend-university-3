package com.archisacademy.service;

import com.archisacademy.dao.InstructorDao;
import com.archisacademy.model.Instructor;

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

}
