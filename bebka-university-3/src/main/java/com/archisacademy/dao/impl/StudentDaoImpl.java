package com.archisacademy.dao.impl;

import com.archisacademy.dao.StudentDao;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {


    @Override
    public List<Student> getAllStudents() {
        Session session = null;
        List<Student> studentList = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            CriteriaQuery<Student> criteriaQuery = session.getCriteriaBuilder().createQuery(Student.class);
            criteriaQuery.from(Student.class);

            studentList = session.createQuery(criteriaQuery).getResultList();

        } catch (Exception e) {
            System.err.println("Tüm öğrencileri getirirken bir hata meydana geldi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return studentList;
    }
}