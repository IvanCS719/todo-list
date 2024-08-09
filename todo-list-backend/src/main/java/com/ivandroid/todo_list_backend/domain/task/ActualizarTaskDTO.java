package com.ivandroid.todo_list_backend.domain.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ActualizarTaskDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        LocalDateTime dueDate
) {
}
