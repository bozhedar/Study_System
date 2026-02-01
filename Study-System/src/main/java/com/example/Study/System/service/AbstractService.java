package com.example.Study.System.service;

import com.example.Study.System.dao.CommonRepository;
import com.example.Study.System.mapper.CommonMapper;
import com.example.Study.System.model.AbstractEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractService <
        E extends AbstractEntity,
        D,
        M extends CommonMapper<E, D>,
        R extends CommonRepository<E>,
        X extends RuntimeException
        >
{
    private final M mapper;
    private final R repository;
    private String classname = this.getClass().getSimpleName();


    public D create(D dto) {
        E entity = repository.save(
                mapper.toEntity(dto)
        );
        log.debug("Created entity: {}", classname);
        return mapper.toDto(entity);
    }

    public D update(Long id, D dto){
        E entity = repository.findById(id).orElseThrow(() ->
        {
            log.warn("{} with id:{} not found", classname, id);
            return createNotFoundException(id);
        });

        mapper.updateFromDto(dto, entity);
        repository.save(entity);

        log.info("{}: {} has been updated", classname, entity.getId());
        return mapper.toDto(entity);
    }

    public void delete(Long id){
        Optional<E> entity = repository.findById(id);

        if (entity.isPresent()) {
            repository.deleteById(id);
        } else  {
            log.warn("{} with id:{} not found", classname, id);
            createNotFoundException(id);
        }
    }

    protected abstract X createNotFoundException(Long id);
}
