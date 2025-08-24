package com.learningdetector.aild.controllers;

import com.learningdetector.aild.entity.Student;
import com.learningdetector.aild.helpers.ExcelHelper;
import com.learningdetector.aild.repositories.StudentRepository;
import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepo;

    public StudentController(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return ResponseEntity.ok(students);
    }


    @PostMapping
    public Student createStudents(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadStudents(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid Excel file.");
        }

        try {
            // Convert Excel file to List<Student>
            List<Student> students = ExcelHelper.excelToStudents(file.getInputStream());

            // Filter out students with duplicate guardian mobile numbers
            List<Student> filteredStudents = students.stream()
                    .filter(s -> !studentRepo.existsByGuardianContact(s.getGuardianContact()))
                    .collect(Collectors.toList());

            if (filteredStudents.isEmpty()) {
                return ResponseEntity.status(409).body("No new students to add. All guardian mobiles already exist.");
            }

            studentRepo.saveAll(filteredStudents);  // bulk save
            return ResponseEntity.ok("Uploaded " + filteredStudents.size() + " new students successfully.");

        } catch (Exception e) {
            e.printStackTrace();  // later replace with logger.error
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return studentRepo.findById(id)
                .map(student -> ResponseEntity.ok(student))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }
    }

}


