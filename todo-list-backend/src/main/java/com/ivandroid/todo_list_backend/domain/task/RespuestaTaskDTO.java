package com.ivandroid.todo_list_backend.domain.task;

import java.time.LocalDateTime;

public record RespuestaTaskDTO(
        Long id,
        String title,
        String description,
        LocalDateTime dueDate
) {
    public RespuestaTaskDTO(Task task) {
        this(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate()
        );
    }
}
