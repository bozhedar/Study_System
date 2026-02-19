package com.example.Study.System.service;

import com.example.Study.System.dao.CourseRepository;
import com.example.Study.System.dao.GroupCourseRepository;
import com.example.Study.System.dao.GroupRepository;
import com.example.Study.System.exception.course.CourseNotFoundException;
import com.example.Study.System.exception.group.GroupNotFoundException;
import com.example.Study.System.mapper.CourseMapper;
import com.example.Study.System.model.CourseEntity;
import com.example.Study.System.model.GroupCourse;
import com.example.Study.System.model.GroupEntity;
import com.example.Study.System.model.dto.CourseDto;
import com.example.Study.System.model.dto.GroupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final GroupRepository groupRepository;
    private final GroupCourseRepository groupCourseRepository;

    public CourseDto create(CourseDto dto) {
        CourseEntity course = courseMapper.toEntity(dto);
        courseRepository.save(course);

        log.info("Course created successfully");
        return courseMapper.toDto(course);
    }

    public CourseDto update(Long id, CourseDto dto) {
        CourseEntity course = getCourseById(id);

        courseMapper.updateFromDto(dto, course);
        courseRepository.save(course);

        log.info("Course updated successfully");
        return courseMapper.toDto(course);
    }

    public void delete(Long id) {
        CourseEntity course = getCourseById(id);

        courseRepository.delete(course);
        log.info("Course deleted successfully");
    }

    public void addGroupOnCourse(Long id, GroupDto dto) {
        CourseEntity course = getCourseById(id);
        GroupEntity group = groupRepository.findByName(dto.name()).orElseThrow(() ->
        {
            log.warn("Group not found");
            return new GroupNotFoundException();
        });

            groupCourseRepository.save(GroupCourse.builder()
                    .group(group)
                    .course(course)
                    .build());
            log.info("Group added successfully");
        }


    private CourseEntity getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            log.error("Course not found");
            return new CourseNotFoundException();
        });
    }
}
