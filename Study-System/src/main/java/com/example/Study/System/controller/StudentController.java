package com.example.Study.System.controller;

import com.example.Study.System.model.dto.GroupDto;
import com.example.Study.System.model.dto.StudentDto;
import com.example.Study.System.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/lms/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/")
    public StudentDto create(@RequestBody @Valid StudentDto dto) {
        return studentService.create(dto);
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody @Valid StudentDto dto) {
        return studentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @PutMapping("/{id}/add_to_group")
    public StudentDto addStudentToGroup(@PathVariable Long id, @RequestBody GroupDto group) {
       return studentService.addStudentToGroup(id, group);
    }

    @GetMapping("/students")
    public Page<StudentDto> getAllStudents(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }
}
