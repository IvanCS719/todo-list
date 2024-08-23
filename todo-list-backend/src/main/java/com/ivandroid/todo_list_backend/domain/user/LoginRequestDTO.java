package com.ivandroid.todo_list_backend.domain.user;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
