package com.ivandroid.todo_list_backend.domain.task;

import java.time.LocalDateTime;

public record RespustaTaskDTO(
        String title,
        String description,
        LocalDateTime dueDate
) {
}
