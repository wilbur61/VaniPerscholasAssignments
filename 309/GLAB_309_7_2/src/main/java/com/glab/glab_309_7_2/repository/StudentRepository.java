package com.glab.glab_309_7_2.repository;

import com.glab.glab_309_7_2.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
    Student findByAccountNo (Long accountNo) ;
    List<Student> findByNameContaining(String name);
}
