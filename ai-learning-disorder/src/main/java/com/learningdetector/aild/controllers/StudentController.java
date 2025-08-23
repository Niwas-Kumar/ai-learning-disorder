package com.learningdetector.aild.controllers;

import com.learningdetector.aild.entity.Student;
import com.learningdetector.aild.repositories.StudentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepo;

    public StudentController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @PostMapping
    public Student createStudents(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudents(@PathVariable Long id) {
        return studentRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepo.deleteById(id);
        return "Student deleted successfully";
    }

}
