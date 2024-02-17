package sba.sms.services;

import jakarta.persistence.TypedQuery;
import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * StudentService is a concrete class. This class implements the
 * StudentI interface, overrides all abstract service methods and
 * provides implementation for each method. Lombok @Log used to
 * generate a logger file.
 */

public class StudentService implements StudentI{

    @Override
    public List<Student> getAllStudents() {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction =session.beginTransaction();
            TypedQuery<Student> query = session.createNamedQuery("getAllStudents", Student.class);
            List<Student> students = query.getResultList();
            transaction.commit();
            return students;
        }catch (Exception e ){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void createStudent(Student student) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction =session.beginTransaction();
            session.persist(student);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentByEmail(String email) {
         try {
             Session session = HibernateUtil.getSessionFactory().openSession();
             TypedQuery<Student> query = session.createNamedQuery("getStudentByEmail", Student.class);
             query.setParameter("email" , email);
             Student student = query.getSingleResult();
             return student;
        }catch (Exception e ){
             e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validateStudent(String email, String password) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            TypedQuery<Student> query = session.createNamedQuery("getStudentByEmail", Student.class);
            query.setParameter("email" , email);
            Student student = query.getSingleResult();
            System.out.println(student);
            if(student.getPassword().equals(password))
                return true;
            else
                return false;
        }catch (Exception e ){
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            TypedQuery<Student> query = session.createNamedQuery("getStudentByEmail", Student.class);
            query.setParameter("email" , email);
            Student student = query.getSingleResult();
            Set<Course> courseList = new HashSet<>();
            courseList.addAll(student.getCourses());
            CourseService cs = new CourseService();
            Course course = cs.getCourseById(courseId);
            if (!isCourseRegistered(courseList,course)) {
                    courseList.add(course);
                    student.setCourses(courseList.stream().collect(Collectors.toList()));
                    session.merge(student);
                    transaction.commit();
                } else {
                    System.out.println(course.getName() + "  Course already registered for " + student.getName());
                }
        }catch (Exception e ){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
public  boolean isCourseRegistered(Set<Course> courseList,Course course){

            for(Course c : courseList){
                if(c.getId() == course.getId())
                    return true;
                else
                    return false;
            }
            return false;

}
    @Override
    public List<Course> getStudentCourses(String email) {
       try {
           Session session = HibernateUtil.getSessionFactory().openSession();
           TypedQuery<Student> query =session.createQuery("SELECT s FROM Student s where email = :email", Student .class);
           // TypedQuery<Student> query = session.createNamedQuery("getStudentByEmail", Student.class);
            query.setParameter("email" , email);
            Student student = query.getSingleResult();

            List<Course> courseList = student.getCourses();
            return courseList;
        }catch (Exception e ){
           e.printStackTrace();
           return null;

       }
    }
}
