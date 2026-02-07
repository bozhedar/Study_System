package com.example.Study.System.exception.teacher;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException() {
        super("Teacher not found");
    }
}
