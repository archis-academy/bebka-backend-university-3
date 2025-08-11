package com.archisacademy.dao.impl;


import com.archisacademy.dao.StudentDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.CourseStudent;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Student createStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            System.out.println("DAO: Öğrenci veritabanına kaydedildi.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void updateStudent(long studentNumber, String newFullName, String newEmail) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.createQuery(
                            "FROM Student WHERE studentNumber = :studentNumber", Student.class)
                    .setParameter("studentNumber", studentNumber)
                    .uniqueResult();

            if (student != null) {
                student.setName(newFullName);
                student.setEmail(newEmail);


                transaction = session.beginTransaction();

                session.update(student);

                transaction.commit();

                System.out.println("Öğrenci güncellendi.");
            } else {
                System.out.println("Öğrenci bulunamadı!");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

        @Override
        public Student getStudentByNumber ( long studentNumber){
            Transaction transaction = null;
            Student student = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                student = session.createQuery(
                                "FROM Student WHERE studentNumber = :studentNumber", Student.class)
                        .setParameter("studentNumber", studentNumber)
                        .uniqueResult();
                transaction.commit();

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.err.println("Öğrenci sorgulama hatası " + e.getMessage());
            }
            return student;
        }

        @Override
        public List<Student> getAllStudents () {
            Session session = null;
            List<Student> students = null;
            Transaction transaction = null;

            try {
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();

                Query<Student> query = session.createQuery("from Student", Student.class);
                students = query.list();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            return students;
        }

        @Override
        public void getRecommendedCoursesForStudent ( long studentId){
            Transaction transaction = null;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                Student student = session.get(Student.class, studentId);
                if (student == null) {
                    System.out.println("Öğrenci bulunamadı. ID: " + studentId);
                    return;
                }

                Query<Course> query = session.createQuery(
                        "SELECT c FROM Course c WHERE c.id NOT IN " +
                                "(SELECT ec.id FROM Student s JOIN s.enrolledCourses ec WHERE s.id = :studentId)", Course.class);

                query.setParameter("studentId", studentId);
                List<Course> recommendedCourses = query.getResultList();

                System.out.println("--- Öğrenci ID " + studentId + " için Önerilen Kurslar Listeleniyor ---");
                if (recommendedCourses.isEmpty()) {
                    System.out.println("Önerilen kurs bulunamadı.");
                } else {
                    System.out.println("Önerilen " + recommendedCourses.size() + " kurs:");
                    for (Course course : recommendedCourses) {
                        System.out.println("- Kurs Adı: " + course.getCourseName());
                    }
                }
                System.out.println("--- Liste Sonlandı ---");

                transaction.commit();

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.err.println("Önerilen kurslar getirilirken bir hata oluştu: " + e.getMessage());
                e.printStackTrace();
            }
        }


        @Override
        public void deleteStudentByNumber ( long studentNumber){
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                Student student = session.createQuery(
                                "FROM Student WHERE studentNumber = :studentNumber", Student.class)
                        .setParameter("studentNumber", studentNumber)
                        .uniqueResult();

                if (student != null) {
                    session.delete(student);
                    System.out.println("Öğrenci silindi.");
                } else {
                    System.out.println("Öğrenci bulunamadı!");
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }

        @Override
        public int getTotalCourseHour ( long studentId){
            int totalCourseHour = 0;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Student student = session.get(Student.class, studentId);

                if (student != null) {
                    List<Course> courses = student.getEnrolledCourses();
                    for (Course course : courses) {
                        totalCourseHour += course.getCourseHour();
                    }
                } else {
                    System.out.println("Öğrenci bulunamadı. ID: " + studentId);
                }
            } catch (Exception e) {
                System.out.println("Hata oluştu: " + e.getMessage());
                e.printStackTrace();
            }

            return totalCourseHour;
        }

      @Override
        public List<Long> getCoursesByStudentId ( long studentId){
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                String sql = "SELECT course_id FROM course_student WHERE student_id = :studentId";
                List<Long> courseIds = session.createNativeQuery(sql)
                        .setParameter("studentId", studentId)
                        .list();
                return courseIds;
            }
        }

        @Override
        public Student getStudentById ( long studentId){
            Transaction tx = null;
            Student student = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                student = session.get(Student.class, studentId);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
            return student;
        }

        @Override
        public String getLetterGrade ( long studentId, long courseId){
            String letterGrade = "";
            Transaction tx = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                CourseStudent courseStudent = session.createQuery(
                                "FROM CourseStudent cs WHERE cs.student.id = :studentId AND cs.course.id = :courseId",
                                CourseStudent.class)
                        .setParameter("studentId", studentId)
                        .setParameter("courseId", courseId)
                        .uniqueResult();
                if (courseStudent != null && courseStudent.getGrade() != null) {
                    double grade = courseStudent.getGrade();

                    if (grade >= 90) {
                        letterGrade = "AA";
                    } else if (grade >= 85) {
                        letterGrade = "BA";
                    } else if (grade >= 80) {
                        letterGrade = "BB";
                    } else if (grade >= 75) {
                        letterGrade = "CB";
                    } else if (grade >= 70) {
                        letterGrade = "CC";
                    } else if (grade >= 65) {
                        letterGrade = "DC";
                    } else if (grade >= 60) {
                        letterGrade = "DD";
                    } else {
                        letterGrade = "FF";
                    }
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            }
            return letterGrade;
        }
    }

