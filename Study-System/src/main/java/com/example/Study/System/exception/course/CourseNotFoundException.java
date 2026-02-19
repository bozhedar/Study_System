package com.example.Study.System.exception.course;

import com.example.Study.System.exception.NotFoundException;

public class CourseNotFoundException extends NotFoundException {
    public CourseNotFoundException() {
        super("Course not found");
    }
}
