package com.archisacademy.dao.impl;

import com.archisacademy.dao.InstructorDao;
import com.archisacademy.model.Course;
import com.archisacademy.model.Instructor;
import com.archisacademy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


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
}
@Override
public Instructor findByInstructorId(Long instructorId) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        return session.get(Instructor.class, instructorId);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

