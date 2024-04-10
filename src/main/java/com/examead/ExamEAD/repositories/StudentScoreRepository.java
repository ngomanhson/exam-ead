package com.examead.ExamEAD.repositories;

import com.examead.ExamEAD.entities.Student;
import com.examead.ExamEAD.entities.StudentScore;
import com.examead.ExamEAD.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {
    StudentScore findByStudentAndSubject(Student student, Subject subject);
}
