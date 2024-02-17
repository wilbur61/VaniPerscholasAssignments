package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Length;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Student is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'student' in the database. A Student object contains fields that represent student
 * login credentials and a join table containing a registered student's email and course(s)
 * data. The Student class can be viewed as the owner of the bi-directional relationship.
 * Implement Lombok annotations to eliminate boilerplate code.
 */
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = "getAllStudents" , query = " from Student "),
        @NamedQuery(name = "getStudentByEmail" , query = " from Student where email =:email"),
})
public class Student {
    @Id
    @Column(name = "email", columnDefinition = "varchar(50)")
    @NonNull
    private String email;

    @NonNull
    @Column(length = 50)
    private String name;

    @ToString.Exclude
    @NonNull
    @Column(length = 50)
    private String password;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.REMOVE, CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_email"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

  /*  public Student(@NonNull String email, @NonNull String name, @NonNull String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email) && Objects.equals(name, student.name) && Objects.equals(password, student.password) && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password, courses);
    }
}



