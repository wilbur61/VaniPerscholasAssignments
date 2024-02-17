package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;
import sba.sms.utils.HibernateUtil;

import java.util.*;

import static org.assertj.core.api.Assertions.*;


class StudentServiceTest {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    public static void setup() {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test
    public void testCreateStudent() {
        System.out.println("Testing CREATE STUDENT...");

        session.beginTransaction();
        String id = "test@gmail.com";
        Student student = new Student(id, "test name", "password");
        StudentService ss = new StudentService();
        assertThat(student.getName()).isEqualTo("test name");
    }
    @Test
    public void testgetAllStudents() {
        System.out.println("Testing GetAllStudent LIST...");

        Query<Student> query = session.createQuery("from Student ", Student.class);
        List<Student> resultList = query.getResultList();
        Assertions.assertFalse(resultList.isEmpty());
    }

    @Test
    public void testGetStudentByEmail() {
        System.out.println("Testing GetStudentByEmail...");

        String id = "test@gmail.com";

        Student student = session.get(Student.class, id);

        Assertions.assertEquals("test name", student.getName());
    }

    @Test
    public void testValidateStudent() {
        System.out.println("Testing ValidateStudent...");

        String id = "test@gmail.com";
        Student student = new Student(id, "test new name", "password");

        session.beginTransaction();
        session.merge(student);
        session.getTransaction().commit();

        Student updatedStudent = session.find(Student.class, id);

        Assertions.assertEquals("test new name", updatedStudent.getName());
    }


    @Test
    public void testRegisterStudentToCourse() {
        System.out.println("Testing RegisterStudentToCourse...");

        String id = "test@gmail.com";

        Student student = session.get(Student.class, id);
        List<Course> courses = new ArrayList<>();
        Course c = new Course("test course","test instructor");
        c.setId(101);

        student.setCourses(courses);

        session.beginTransaction();
        session.merge(student);
        session.getTransaction().commit();

        Student updatedStudent = session.get(Student.class, id);

        Assertions.assertIterableEquals(courses,updatedStudent.getCourses());
    }

    @Test
    public void testGetStudentCourses() {
        System.out.println("Testing GetStudentCourses...");

        String id = "test@gmail.com";

        Student student = session.get(Student.class, id);
        Set<Course> courses = new HashSet<>();
        Course c = new Course("test course","test instructor");
        c.setId(101);

        Assertions.assertIterableEquals(student.getCourses(),courses);
        CourseService cs = new CourseService();

        courses.add(cs.getCourseById(10));
    }




    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }

}