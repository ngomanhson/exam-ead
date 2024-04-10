package com.examead.ExamEAD.services;

import com.examead.ExamEAD.dtos.StudentDTO;
import com.examead.ExamEAD.dtos.StudentInformation;
import com.examead.ExamEAD.dtos.StudentScoreDTO;
import com.examead.ExamEAD.models.StudentScoreCreate;
import com.examead.ExamEAD.models.StudentCreate;

import java.util.List;

public interface IStudentService {
    List<StudentInformation> getInfomationStudents();
    StudentDTO create(StudentCreate model);
    StudentScoreDTO insertScore(StudentScoreCreate model);
}
