package com.archisacademy.service;

import com.archisacademy.dao.InstructorDao;
import com.archisacademy.model.Instructor;

public class InstructorService {

    private final InstructorDao instructorDao;

    public InstructorService(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    public void createInstructor(String instructorName,long instructorNumber,String email,String password) {
            Instructor instructor=new Instructor(instructorName,instructorNumber,email,password);
            instructorDao.createInstructor(instructor);
    }

    public void deleteInstructor(long instructorNumber) {
        instructorDao.deleteInstructor(instructorNumber);
    }
}
