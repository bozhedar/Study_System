package com.example.Study.System.service;

import com.example.Study.System.dao.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TaskScheduledService {
    private final ScheduleRepository scheduleRepository;

    @Scheduled(cron = "${task.delete-old-schedules.cron}")
    public void deleteOldSchedules() {
        int deletedCount = scheduleRepository
                .deleteAllByDateTimeBefore(
                        LocalDateTime.now().minusYears(1)
                );

        log.info("{} schedules deleted", deletedCount);
    }
}
