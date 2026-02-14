package com.example.Study.System.exception.group;

import com.example.Study.System.exception.NotFoundException;

public class GroupNotFoundException extends NotFoundException {
    public GroupNotFoundException() {
        super("Group not found");
    }
}
