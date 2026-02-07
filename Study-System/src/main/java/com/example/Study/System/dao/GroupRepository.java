package com.example.Study.System.dao;

import com.example.Study.System.model.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Long> {
    boolean existsByName(String name);
}
