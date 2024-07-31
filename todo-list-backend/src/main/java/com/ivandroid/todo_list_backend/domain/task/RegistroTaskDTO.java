package com.ivandroid.todo_list_backend.domain.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistroTaskDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        LocalDateTime dueDate,
        @NotNull
        Long userId
) {
}
