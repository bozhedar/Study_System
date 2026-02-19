package com.example.Study.System.exception.student;

import com.example.Study.System.exception.NotFoundException;

public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException() {
        super("Student not found");
    }
}
