package com.archisacademy.dao.impl;

import com.archisacademy.dao.CourseDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CourseDaoImpl implements CourseDao {

    @Override
    public Course addCourse(Course course) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<Student> enrolledStudents  = new ArrayList<>();
            for (Student student : course.getEnrolledStudents()) {
                Student dbStudent = session.get(Student.class, student.getId());
                if (dbStudent != null) {
                    enrolledStudents.add(dbStudent);
                }
            }
            course.setEnrolledStudents(enrolledStudents);

            session.save(course);
            transaction.commit();
            System.out.println("Course added successfully.");
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Course existingCourse = session.createQuery(
                            "FROM Course WHERE courseNumber = :courseNumber", Course.class)
                    .setParameter("courseNumber", course.getCourseNumber())
                    .uniqueResult();

            if (existingCourse != null) {
                existingCourse.setCourseName(course.getCourseName());
                existingCourse.setCourseInstructor(course.getCourseInstructor());
                session.update(existingCourse);
                System.out.println("Course updated successfully.");
            } else {
                System.out.println("Course with number " + course.getCourseNumber() + " not found.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourseById(long courseId) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Course course = session.get(Course.class, courseId);
            if (course != null) {
                session.delete(course);
                System.out.println("Kurs başarıyla silindi. ID: " + courseId);
            } else {
                System.out.println("Kurs bulunamadı. ID: " + courseId);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Kurs silinirken hata oluştu: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Course> getAllCourses() {
        Transaction tx = null;
        List<Course> courses = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            courses = session.createQuery("SELECT c from Course c JOIN FETCH c.courseInstructor", Course.class)
                    .list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public List<Course> getPopularCourses(int topCount) {
        Transaction tx = null;
        List<Course> courses = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            courses = session.createQuery(
                            "SELECT c FROM Course c " +
                                    "LEFT JOIN c.enrolledStudents s " +
                                    "GROUP BY c " +
                                    "ORDER BY COUNT(s) DESC", Course.class)
                    .setMaxResults(topCount)
                    .list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public Course getCourseById(Long id) {
        Transaction transaction = null;
        Course course = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            course = session.get(Course.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public Course getCourseByNumber(Long courseNumber) {
        Transaction transaction = null;
        Course course = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
             return session.createQuery("FROM Course c WHERE c.courseNumber = :courseNumber", Course.class)
            .setParameter("courseNumber", courseNumber)
                     .uniqueResult();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public List<Course> getCoursesByStudentId(long id) {
        List <Course> enrolledCourses=null;
        Transaction transaction=null;
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            transaction= session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                enrolledCourses = student.getEnrolledCourses();
            } else {
                System.out.println("DAO: Öğrenci bulunamadı!!!");
            }
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return enrolledCourses;
    }

    public List<Course> searchCoursesByName(String searchCriteria, Map<String, String> filters) {

        List<Course> result = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StringBuilder hql = new StringBuilder("FROM Course c JOIN FETCH c.courseInstructor WHERE LOWER(c.courseName) LIKE :searchCriteria");

            if (filters != null) {
                if (filters.get("instructorId") != null) {
                    hql.append(" AND c.courseInstructor.id = :instructorId");
                }
            }

            Query<Course> query = session.createQuery(hql.toString(), Course.class);
            query.setParameter("searchCriteria", "%" + searchCriteria.toLowerCase() + "%");

            if (filters != null && filters.get("instructorId") != null) {
                long instructorId = Long.parseLong(filters.get("instructorId"));
                query.setParameter("instructorId", instructorId);
            }

            result = query.getResultList();

            for (Course course : result) {
                System.out.println("Kurs Adı: " + course.getCourseName()
                        + " | Kurs Numarası: " + course.getCourseNumber()
                        + " | Eğitmen: " + course.getCourseInstructor().getInstructorName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

