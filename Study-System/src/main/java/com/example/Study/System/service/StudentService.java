package com.example.Study.System.service;

import com.example.Study.System.dao.StudentRepository;
import com.example.Study.System.exception.student.StudentNotFoundException;
import com.example.Study.System.mapper.StudentMapper;
import com.example.Study.System.model.StudentEntity;
import com.example.Study.System.model.dto.StudentDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentDto create(StudentDto studentDto) {
        StudentEntity student = studentMapper.toEntity(studentDto);
        studentRepository.save(student);

        log.info("Student {} has been created", student.getId());
        return studentMapper.toDto(student);
    }

    public StudentDto update(Long id, StudentDto dto) {
        StudentEntity student = studentRepository.findById(id).orElseThrow(() ->
                {
                    log.warn("Updating student not found");
                    return new StudentNotFoundException();
                });

        studentMapper.updateFromDto(dto, student);
        studentRepository.save(student);

        log.info("Student {} has been updated", student.getId());
        return studentMapper.toDto(student);
    }

    public void delete(Long id) {
        Optional<StudentEntity> student =  studentRepository.findById(id);

        if (student.isPresent()) {
            studentRepository.deleteById(id);
            log.info("Student {} has been deleted", student.get().getId());
        } else {
            log.warn("Deleting student not found");
            throw new StudentNotFoundException();
        }

    }

    public StudentDto addStudentToGroup(Long id, String groupName) {
       //TODO
        return null;
    }
}
