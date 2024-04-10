package com.examead.ExamEAD.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SubjectScore {
    private String subjectName;

    private Double score1;

    private Double score2;

    private int credit;

    private String grade;
}
