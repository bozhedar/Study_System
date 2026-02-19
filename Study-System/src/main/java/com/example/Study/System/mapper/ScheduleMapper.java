package com.example.Study.System.mapper;

import com.example.Study.System.model.ScheduleEntity;
import com.example.Study.System.model.dto.ScheduleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleDto toDto(ScheduleEntity entity);
    ScheduleEntity toEntity(ScheduleDto dto);

    List<ScheduleDto> toDtos(List<ScheduleEntity> entities);
}
