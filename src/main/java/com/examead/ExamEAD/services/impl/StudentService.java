package com.examead.ExamEAD.services.impl;

import com.examead.ExamEAD.dtos.StudentDTO;
import com.examead.ExamEAD.dtos.StudentInformation;
import com.examead.ExamEAD.dtos.StudentScoreDTO;
import com.examead.ExamEAD.dtos.SubjectScore;
import com.examead.ExamEAD.entities.Student;
import com.examead.ExamEAD.entities.StudentScore;
import com.examead.ExamEAD.entities.Subject;
import com.examead.ExamEAD.models.StudentScoreCreate;
import com.examead.ExamEAD.models.StudentCreate;
import com.examead.ExamEAD.repositories.StudentRepository;
import com.examead.ExamEAD.repositories.StudentScoreRepository;
import com.examead.ExamEAD.repositories.SubjectRepository;
import com.examead.ExamEAD.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Override
    public List<StudentInformation> getInfomationStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentInformation> studentInformationList = new ArrayList<>();
        for (Student student : studentList) {
            List<SubjectScore> subjectScoreList = student.getStudentScoreList().stream().map(this::toSubjectScore)
                    .collect(Collectors.toList());
            StudentInformation studentInformation = StudentInformation.builder()
                    .id(student.getId())
                    .fullName(student.getFullName())
                    .studentCode(student.getStudentCode())
                    .subjectScoreList(subjectScoreList)
                    .build();
            studentInformationList.add(studentInformation);
        }
        return studentInformationList;
    }

    @Override
    public StudentDTO create(StudentCreate model) {
        Student studentExisting = studentRepository.findByStudentCode(model.getStudentCode());
        if (studentExisting != null)
            throw new RuntimeException("Students already exist");
        Student student = Student.builder()
                .studentCode(model.getStudentCode())
                .fullName(model.getFullName())
                .address(model.getAddress())
                .build();
        studentRepository.save(student);
        StudentDTO studentDTO = StudentDTO.builder()
                .id(student.getId())
                .studentCode(student.getStudentCode())
                .fullName(student.getFullName())
                .address(student.getAddress())
                .build();
        return studentDTO;
    }

    @Override
    public StudentScoreDTO insertScore(StudentScoreCreate model) {
        Student studentExisting = studentRepository.findById(model.getStudentId())
                .orElseThrow(() -> new RuntimeException("No student found!"));
        Subject subjectExisting = subjectRepository.findById(model.getSubjectId())
                .orElseThrow(() -> new RuntimeException("No subject found!"));
        StudentScore studentScoreExisting = studentScoreRepository.findByStudentAndSubject(
                studentExisting,
                subjectExisting);
        if (studentScoreExisting != null)
            throw new RuntimeException("The student's subject has a grade");

        StudentScore studentScore = StudentScore.builder()
                .student(studentExisting)
                .subject(subjectExisting)
                .score1(model.getScore1())
                .score2(model.getScore2())
                .build();
        studentScoreRepository.save(studentScore);

        StudentScoreDTO studentScoreDTO = StudentScoreDTO.builder()
                .id(studentScore.getId())
                .studentName(studentScore.getStudent().getFullName())
                .subjectName(studentScore.getSubject().getSubjectName())
                .score1(studentScore.getScore1())
                .score2(studentScore.getScore2())
                .build();
        return studentScoreDTO;
    }

    private SubjectScore toSubjectScore(StudentScore studentScore) {
        if (studentScore == null)
            throw new RuntimeException("Not found");
        SubjectScore subjectScore = SubjectScore.builder()
                .subjectName(studentScore.getSubject().getSubjectName())
                .score1(studentScore.getScore1())
                .score2(studentScore.getScore2())
                .credit(studentScore.getSubject().getCredit())
                .grade(calculateGrade(
                        studentScore.getScore1(),
                        studentScore.getScore2()))
                .build();
        return subjectScore;
    }

    private String calculateGrade(Double score1, Double score2) {
        double grade = 0.3 * score1 + 0.7 * score2;

        if (grade >= 8.0) {
            return "A";
        } else if (grade >= 6.0) {
            return "B";
        } else if (grade >= 4.0) {
            return "D";
        } else {
            return "F";
        }
    }
}
