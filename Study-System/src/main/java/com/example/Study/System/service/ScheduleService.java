package com.example.Study.System.service;

import com.example.Study.System.dao.ScheduleRepository;
import com.example.Study.System.exception.schedule.ScheduleNotFoundException;
import com.example.Study.System.mapper.ScheduleMapper;
import com.example.Study.System.model.ScheduleEntity;
import com.example.Study.System.model.dto.ScheduleDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleDto addSchedule(ScheduleDto dto) {
        ScheduleEntity entity = scheduleMapper.toEntity(dto);
        scheduleRepository.save(entity);
        log.info("Schedule Added Successfully");
        return scheduleMapper.toDto(scheduleRepository.save(entity));
    }

    public ScheduleDto updateTime(Long id, ScheduleDto dto) {
        ScheduleEntity entity = getScheduleEntityById(id);
        entity.setDateTime(dto.dateTime());

        scheduleRepository.save(entity);
        log.info("Schedule Updated Successfully");

        return scheduleMapper.toDto(scheduleRepository.save(entity));
    }

    public void deleteById(Long id) {
        ScheduleEntity entity = getScheduleEntityById(id);
        scheduleRepository.delete(entity);
    }



    public List<ScheduleDto> getGroupSchedule(Long groupId) {
        return scheduleMapper.toDtos(
                scheduleRepository.getScheduleByGroupId(groupId)
        );
    }

    public List<ScheduleDto> getTeacherSchedule(Long teacherId) {
        return scheduleMapper.toDtos(
                scheduleRepository.getScheduleByTeacherId(teacherId)
        );
    }

    private ScheduleEntity getScheduleEntityById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> {
            log.error("Schedule Not Found");
            return new ScheduleNotFoundException();
        });
    }
}
