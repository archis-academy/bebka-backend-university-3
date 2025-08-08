package com.archisacademy.dao.impl;

import com.archisacademy.dao.CourseStudentDao;
import com.archisacademy.dao.InstructorDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.CourseStudent;
import com.archisacademy.model.Instructor;
import com.archisacademy.model.Student;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class InstructorDaoImpl implements InstructorDao {

    @Override
    public Instructor createInstructor(Instructor instructor) {
        // 2. Hibernate Session ve Transaction başlat
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // 3. Instructor nesnesini kaydet
            session.save(instructor);

            // 4. Commit
            transaction.commit();

            System.out.println("Instructor başarıyla kaydedildi!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return instructor;
    }

    @Override
    public void deleteInstructor(long instructorNumber) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            //instructorNumber'a göre instructor bulma
            Instructor instructor = session.createQuery(
                            "FROM Instructor WHERE instructorNumber = :instructorNumber", Instructor.class)
                    .setParameter("instructorNumber", instructorNumber)
                    .uniqueResult();
            if  (instructor != null) {
                session.delete(instructor);
            } else {
                System.out.println("Eğitmen bulunamadı!");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateInstructor(long instructorNumber, String newInstructorName, String newEmail) {
        Transaction transaction = null;

        try( Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Instructor instructor = session.createQuery(
                    "FROM Instructor WHERE instructorNumber = :instructorNumber", Instructor.class)
                    .setParameter("instructorNumber", instructorNumber)
                    .uniqueResult();

            if(instructor != null){
                instructor.setInstructorName(newInstructorName);
                instructor.setEmail(newEmail);

                session.update(instructor);

                System.out.println("Eğitmen güncellendi");
            } else {
                System.out.println("Eğitmen bulunamadı");
            }

            transaction.commit();;

        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            instructors = session.createQuery("FROM Instructor", Instructor.class).getResultList();

            if (instructors != null ) {
                System.out.println("Veritabanından çekilen eğitmenler:");
                for (Instructor instructor : instructors) {
                    System.out.println("Eğitmen: " + instructor.getInstructorName() +
                            ", Numarası: " + instructor.getInstructorNumber() +
                            ", Email: " + instructor.getEmail());

                    List<Course> courses = instructor.getTaughtCourses();
                    if (courses != null && !courses.isEmpty()) {
                        for (Course course : courses) {
                            System.out.println("    Kurs: " + course.getCourseName());
                        }
                    } else {
                        System.out.println("    Bu eğitmenin henüz dersi yok.");
                    }
                }
            } else {
                System.out.println("Sistemde kayıtlı eğitmen bulunamadı.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return instructors;
    }

    @Override
    public Instructor findByInstructorNumber(long instructorNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Instructor i WHERE i.instructorNumber= :number", Instructor.class)
                    .setParameter("number", instructorNumber)
                    .uniqueResult();

        } catch (Exception e) {
            System.out.println("Eğitmen aranırken Hata");
            return null;
        }
    }

    @Override
    public void getTopRecommendedCourses(long instructorId, int topCount) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Course> query = session.createQuery(
                    "SELECT c FROM Course c LEFT JOIN c.enrolledStudents s WHERE c.courseInstructor.id = :instructorId GROUP BY c.id ORDER BY COUNT(s.id) DESC", Course.class
            );

            query.setParameter("instructorId", instructorId);
            query.setMaxResults(topCount);

            List<Course> recommendedCourses = query.getResultList();


            System.out.println("--- En Çok Önerilen Kurslar Listeleniyor ---");
            if (recommendedCourses.isEmpty()) {
                System.out.println(instructorId + " ID'li eğitmenin hiç kursu bulunmamakta veya kurslarına öğrenci kayıtlı değil.");
            } else {
                System.out.println(instructorId + " ID'li eğitmen için en çok önerilen " + recommendedCourses.size() + " kurs:");
                for (Course course : recommendedCourses) {
                    System.out.println("- Kurs Adı: " + course.getCourseName() + ", Kayıtlı Öğrenci Sayısı: " + course.getEnrolledStudents().size());
                }
            }
            System.out.println("--- Liste Sonlandı ---");

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("En çok önerilen kurslar listelenirken bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public double getAverageGradeByInstructorId(long instructorId) {

        double total = 0;
        int count = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Instructor’a ait tüm not kayıtlarını getiriyoruz
            String hql = "SELECT cs.grade " +
                    "FROM CourseStudent cs " +
                    "JOIN cs.course c " +
                    "WHERE c.courseInstructor.id = :instructorId AND cs.grade IS NOT NULL";

            List<Double> grades = session.createQuery(hql, Double.class)
                    .setParameter("instructorId", instructorId)
                    .getResultList();

            for (Double grade : grades) {
                total += grade;
                count++;
            }

            return count > 0 ? total / count : 0.0;
        }
    }

    @Override
    public long getTotalStudentCountByInstructorId(long instructorId) {
        long studentCount = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Instructor instructor = session.get(Instructor.class, instructorId);
            if (instructor == null) {
                return 0;
            }

            List<Course> taughtCourses = instructor.getTaughtCourses();
            if (taughtCourses == null || taughtCourses.isEmpty()) {
                return 0;
            }

            Set<Long> uniqueStudentIds = new HashSet<>();
            for (Course course : taughtCourses) {
                List<Student> students = course.getEnrolledStudents();
                if (students != null) {
                    for (Student student : students) {
                        uniqueStudentIds.add(student.getId());
                    }
                }
            }

            studentCount = uniqueStudentIds.size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentCount;
    }
}

