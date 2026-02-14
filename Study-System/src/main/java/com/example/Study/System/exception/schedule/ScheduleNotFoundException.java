package com.example.Study.System.exception.schedule;

import com.example.Study.System.exception.NotFoundException;

public class ScheduleNotFoundException extends NotFoundException {
    public ScheduleNotFoundException() {
        super("Schedule Not Found");
    }
}
