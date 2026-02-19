package com.example.Study.System.model.dto;

import java.time.LocalDateTime;

public record ScheduleDto(
        Long groupId,
        Long teacherId,
        Long courseId,
        LocalDateTime dateTime
) {
}
