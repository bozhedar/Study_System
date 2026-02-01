package com.example.Study.System.dao;

import com.example.Study.System.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository <E extends AbstractEntity> extends JpaRepository<E, Long>{
}
