package com.example.Study.System.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CourseDto(
        @NotBlank(message = "Name is required")
        @Size(max = 32, message = "Name must not exceed 32 characters")
        String name,
        String description
) {
}
