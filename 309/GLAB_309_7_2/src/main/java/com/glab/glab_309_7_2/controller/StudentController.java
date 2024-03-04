package com.glab.glab_309_7_2.controller;

import com.glab.glab_309_7_2.model.Student;
import com.glab.glab_309_7_2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    // Read all Student Data
    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<Student> students = (List<Student>) studentRepository.findAll();
        model.addAttribute("students",students);
        return "students";//(List<Student>) studentRepository.findAll();
    }
    @GetMapping("/students/Add")
    public String createStudentForm(Model model){
        model.addAttribute("student" , new Student());
        return "studentForm";
    }
    @GetMapping("students/Update/{id}")
    public String createStudentUpdateForm(@PathVariable("id") long stuid,Model model){
        model.addAttribute("student",studentRepository.findById(stuid));
        return "studentUpdateForm";
    }
    @GetMapping("students/deletebyid/{id}")
    public String deleteStudent(@PathVariable("id") long stuid){
        studentRepository.deleteById(stuid);
        return "redirect:/students";
    }
    //Read or creating Student by Account number
    @GetMapping("/students/{accountNo}" )
    public Student findstudentbyaccont(@PathVariable Long accountNo) {
        return studentRepository.findByAccountNo(accountNo);
    }
    //add student detail in the database
    @PostMapping("/AddStudent")
    public String saveStudent(@ModelAttribute("student") Student student)
    {
        studentRepository.save(student);
        return "redirect:/students";
    }
    @DeleteMapping("/deletebyid/{id}")
    public String deleteStudentbyId(@PathVariable("id") long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
    @DeleteMapping("/deleteAll")
    public void deleteAllStudent() {
        studentRepository.deleteAll();
    }
    @PostMapping("/UpdateStudent/{id}")
    private String  updateStudent(@PathVariable("id") long stuid,@ModelAttribute("student") Student student,Model model) {
        Optional<Student> stuData = studentRepository.findById(stuid);
        if (stuData.isPresent()) {
            Student s = stuData.get();
            s.setName(student.getName());
            s.setAccountNo(student.getAccountNo());
            s.setId(student.getId());
            s.setMarks(student.getMarks());
            studentRepository.save(s);
        }
        return "redirect:/students";
    }
}



