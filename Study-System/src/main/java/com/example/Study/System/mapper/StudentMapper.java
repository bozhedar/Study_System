package com.example.Study.System.mapper;

import com.example.Study.System.model.GroupEntity;
import com.example.Study.System.model.StudentEntity;
import com.example.Study.System.model.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper  {
    StudentDto toDto(StudentEntity entity);
    StudentEntity toEntity(StudentDto dto);
    void updateFromDto(StudentDto dto, @MappingTarget StudentEntity entity);

    default String mapGroupName(GroupEntity group) {
        return group != null ? group.getName() : null;
    }
}
