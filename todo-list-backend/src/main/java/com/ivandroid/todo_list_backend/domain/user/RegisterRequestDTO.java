package com.ivandroid.todo_list_backend.domain.user;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
        @NotBlank
        String username,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
