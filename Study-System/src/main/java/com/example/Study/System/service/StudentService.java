package com.example.Study.System.service;

import com.example.Study.System.dao.GroupRepository;
import com.example.Study.System.dao.StudentRepository;
import com.example.Study.System.exception.group.GroupNotFoundException;
import com.example.Study.System.exception.student.StudentNotFoundException;
import com.example.Study.System.mapper.StudentMapper;
import com.example.Study.System.model.GroupEntity;
import com.example.Study.System.model.StudentEntity;
import com.example.Study.System.model.dto.GroupDto;
import com.example.Study.System.model.dto.StudentDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final GroupRepository groupRepository;

    public StudentDto create(StudentDto studentDto) {
        StudentEntity student = studentMapper.toEntity(studentDto);
        studentRepository.save(student);

        log.info("Student {} has been created", student.getId());
        return studentMapper.toDto(student);
    }

    public StudentDto update(Long id, StudentDto dto) {
        StudentEntity student = getStudentById(id);

        studentMapper.updateFromDto(dto, student);
        studentRepository.save(student);

        log.info("Student {} has been updated", student.getId());
        return studentMapper.toDto(student);
    }

    public void delete(Long id) {
        StudentEntity student = getStudentById(id);
        studentRepository.delete(student);
    }

    public StudentDto addStudentToGroup(Long id, GroupDto group) {
        if (groupRepository.findByName(group.name()) != null) {

            StudentEntity student = getStudentById(id);
            GroupEntity groupEntity = groupRepository.findByName(group.name());
            student.setGroup(groupEntity);
            studentRepository.save(student);

            log.info("Student {} has been added to group", id);
            return studentMapper.toDto(student);

        } else {
            log.error("Group not found");
            throw new GroupNotFoundException();
        }

    }

    private StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
        {
            log.warn("Student not found");
            return new StudentNotFoundException();
        });
    }
}
