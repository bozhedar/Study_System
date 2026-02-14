package com.example.Study.System.exception.teacher;

import com.example.Study.System.exception.NotFoundException;

public class TeacherNotFoundException extends NotFoundException {
    public TeacherNotFoundException() {
        super("Teacher not found");
    }
}
