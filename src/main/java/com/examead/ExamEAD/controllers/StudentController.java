package com.examead.ExamEAD.controllers;

import com.examead.ExamEAD.dtos.ResponseObject;
import com.examead.ExamEAD.dtos.StudentDTO;
import com.examead.ExamEAD.dtos.StudentInformation;
import com.examead.ExamEAD.dtos.StudentScoreDTO;
import com.examead.ExamEAD.models.StudentScoreCreate;
import com.examead.ExamEAD.models.StudentCreate;
import com.examead.ExamEAD.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = "*")

public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAll() {
        try {
            List<StudentInformation> list = studentService.getInfomationStudents();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "ok", list)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, e.getMessage(), "")
            );
        }
    }

    @PostMapping("")
    ResponseEntity<ResponseObject> insertStudent(@RequestBody StudentCreate createStudent) {
        try {
            StudentDTO studentDTO = studentService.create(createStudent);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "ok", studentDTO)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, e.getMessage(), "")
            );
        }
    }

    @PostMapping("/score")
    ResponseEntity<ResponseObject> insertScoreStudent(@RequestBody StudentScoreCreate studentScoreCreate) {
        try {
            StudentScoreDTO studentScoreDTO = studentService.insertScore(studentScoreCreate);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "ok", studentScoreDTO)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, e.getMessage(), "")
            );
        }
    }
}
