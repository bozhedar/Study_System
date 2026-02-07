package com.example.Study.System.exception.group;

public class GroupExistsException extends RuntimeException {
    public GroupExistsException() {
        super("Group already exists");
    }
}
