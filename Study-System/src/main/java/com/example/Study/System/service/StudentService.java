package com.example.Study.System.service;

import com.example.Study.System.dao.StudentRepository;
import com.example.Study.System.exception.StudentNotFoundException;
import com.example.Study.System.mapper.StudentMapper;
import com.example.Study.System.model.StudentEntity;
import com.example.Study.System.model.dto.StudentDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
public class StudentService extends AbstractService<StudentEntity, StudentDto, StudentMapper, StudentRepository, StudentNotFoundException>{


    public StudentService(StudentMapper mapper, StudentRepository repository) {
        super(mapper, repository);
    }

    @Override
    protected StudentNotFoundException createNotFoundException(Long id) {
        return new StudentNotFoundException(id);
    }
}
