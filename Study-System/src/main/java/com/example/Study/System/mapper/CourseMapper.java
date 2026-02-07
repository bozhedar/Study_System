package com.example.Study.System.mapper;

import com.example.Study.System.model.CourseEntity;
import com.example.Study.System.model.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDto toDto(CourseEntity entity);
    CourseEntity toEntity(CourseDto dto);
    void updateFromDto(CourseDto dto, @MappingTarget CourseEntity entity);
}
