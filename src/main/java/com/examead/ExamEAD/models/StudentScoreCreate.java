package com.examead.ExamEAD.models;

import lombok.Data;

@Data
public class StudentScoreCreate {
    private Long studentId;

    private Long subjectId;

    private Double score1;

    private Double score2;
}
