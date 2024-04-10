package com.examead.ExamEAD.repositories;

import com.examead.ExamEAD.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentCode(String code);
}
