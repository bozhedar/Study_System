package com.example.Study.System.mapper;

import com.example.Study.System.model.StudentEntity;
import com.example.Study.System.model.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper extends CommonMapper<StudentEntity, StudentDto> {

}
