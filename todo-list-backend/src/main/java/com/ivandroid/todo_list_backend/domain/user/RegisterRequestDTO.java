package com.ivandroid.todo_list_backend.domain.user;

/*@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    String username;
    String email;
    String password;
}*/

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
