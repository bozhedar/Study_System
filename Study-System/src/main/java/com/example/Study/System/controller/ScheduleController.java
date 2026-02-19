package com.example.Study.System.controller;

import com.example.Study.System.model.dto.ScheduleDto;
import com.example.Study.System.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/lms/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/")
    public ScheduleDto addSchedule(@RequestBody ScheduleDto dto) {
        return scheduleService.addSchedule(dto);
    }

    @PutMapping("/{id}")
    public ScheduleDto updateTime(@PathVariable Long id, @RequestBody ScheduleDto dto) {
        return scheduleService.updateTime(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteById(id);
    }

    @GetMapping("/group/{groupId}")
    public List<ScheduleDto> getGroupSchedule(@PathVariable Long groupId) {
        return scheduleService.getGroupSchedule(groupId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<ScheduleDto> getTeacherSchedule(@PathVariable Long teacherId) {
        return scheduleService.getTeacherSchedule(teacherId);
    }
}
