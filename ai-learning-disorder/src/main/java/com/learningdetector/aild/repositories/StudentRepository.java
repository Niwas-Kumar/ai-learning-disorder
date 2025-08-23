package com.learningdetector.aild.repositories;

import com.learningdetector.aild.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
