package com.example.Study.System.dao;

import com.example.Study.System.model.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> getScheduleByGroupId(Long groupId);

    List<ScheduleEntity> getScheduleByTeacherId(Long teacherId);
}
