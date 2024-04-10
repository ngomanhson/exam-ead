package com.examead.ExamEAD.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "subject_t")
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_code", nullable = false, unique = true)
    private String subjectCode;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "credit", nullable = false)
    private int credit;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<StudentScore> studentScoreList;
}
