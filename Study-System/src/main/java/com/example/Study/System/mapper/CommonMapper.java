package com.example.Study.System.mapper;

import com.example.Study.System.model.AbstractEntity;
import org.mapstruct.MappingTarget;

public interface CommonMapper <E extends AbstractEntity, D>{
    D toDto(E entity);
    E toEntity(D dto);
    void updateFromDto(D dto, @MappingTarget E entity);
}
