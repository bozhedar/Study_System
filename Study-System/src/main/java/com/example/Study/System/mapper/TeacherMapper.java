package com.example.Study.System.mapper;

import com.example.Study.System.model.TeacherEntity;
import com.example.Study.System.model.dto.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDto toDto(TeacherEntity entity);
    TeacherEntity toEntity(TeacherDto dto);
    void updateFromDto(TeacherDto dto, @MappingTarget TeacherEntity entity);
}
