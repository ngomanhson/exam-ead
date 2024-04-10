package com.examead.ExamEAD.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StudentDTO {
    private Long id;

    private String studentCode;

    private String fullName;

    private String address;
}
