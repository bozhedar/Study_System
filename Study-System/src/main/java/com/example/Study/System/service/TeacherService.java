package com.example.Study.System.service;

import com.example.Study.System.dao.TeacherRepository;
import com.example.Study.System.exception.teacher.TeacherNotFoundException;
import com.example.Study.System.mapper.TeacherMapper;
import com.example.Study.System.model.TeacherEntity;
import com.example.Study.System.model.dto.TeacherDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherDto create(TeacherDto dto) {
        TeacherEntity teacher = teacherMapper.toEntity(dto);
        teacherRepository.save(teacher);

        log.info("Teacher {} has been created", teacher.getId());
        return teacherMapper.toDto(teacher);
    }

    public TeacherDto update(Long id, TeacherDto dto) {
        TeacherEntity teacher = teacherRepository.findById(id).orElseThrow(() ->
        {
            log.warn("Updating teacher not found");
            return new TeacherNotFoundException();
        });

        teacherMapper.updateFromDto(dto, teacher);
        teacherRepository.save(teacher);

        log.info("Teacher {} has been updated", teacher.getId());
        return teacherMapper.toDto(teacher);
    }

    public void delete(Long id) {
        Optional<TeacherEntity> teacher = teacherRepository.findById(id);

        if (teacher.isPresent()) {
            teacherRepository.deleteById(id);
            log.info("Teacher {} has been deleted", teacher.get().getId());
        } else {
            log.warn("Deleting teacher not found");
            throw new TeacherNotFoundException();
        }
    }
}
