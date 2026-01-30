package com.example.Study.System.controller;

import com.example.Study.System.model.dto.StudentDto;
import com.example.Study.System.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/lms/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/new")
    public StudentDto create(StudentDto studentDto) {
        return studentService.create(studentDto);
    }
}
