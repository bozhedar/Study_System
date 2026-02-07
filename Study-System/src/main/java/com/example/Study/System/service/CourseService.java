package com.example.Study.System.service;

import com.example.Study.System.dao.CourseRepository;
import com.example.Study.System.exception.course.CourseNotFoundException;
import com.example.Study.System.mapper.CourseMapper;
import com.example.Study.System.model.CourseEntity;
import com.example.Study.System.model.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseDto create(CourseDto dto) {
        CourseEntity course = courseMapper.toEntity(dto);
        courseRepository.save(course);

        log.info("Course created successfully");
        return courseMapper.toDto(course);
    }

    public CourseDto update(Long id, CourseDto dto) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> {
            log.error("Course not found");
            return new CourseNotFoundException();
        });

        courseMapper.updateFromDto(dto, course);
        courseRepository.save(course);

        log.info("Course updated successfully");
        return courseMapper.toDto(course);
    }

    public void delete(Long id) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> {
            log.error("Course not found");
            return new CourseNotFoundException();
        });

        courseRepository.delete(course);
        log.info("Course deleted successfully");
    }
}
