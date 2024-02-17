package sba.sms.services;

import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CourseService is a concrete class. This class implements the
 * CourseI interface, overrides all abstract service methods and
 * provides implementation for each method.
 */
public class CourseService implements CourseI {

    @Override
    public void createCourse(Course course) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
    }

    @Override
    public Course getCourseById(int courseId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            TypedQuery<Course> query = session.createNamedQuery("getCourseById", Course.class);
            query.setParameter("id", courseId);
            Course c = query.getSingleResult();
            return c;
        } catch (Exception e) {
            System.out.println("Exception occured could not get course.");
            return null;
        }

    }

    @Override
    public List<Course> getAllCourses() {
       try{
           Session session = HibernateUtil.getSessionFactory().openSession();
           TypedQuery<Course> query = session.createNamedQuery("getAllCourses",Course.class);
           List<Course> results = query.getResultList();
           return results;
       }catch(Exception e){
           System.out.println("Exception occured could not get all courses.");
           return null;
       }

    }
}
