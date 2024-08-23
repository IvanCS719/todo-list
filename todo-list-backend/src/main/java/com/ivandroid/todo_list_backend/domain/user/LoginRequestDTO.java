package com.ivandroid.todo_list_backend.domain.user;

import jakarta.validation.constraints.NotNull;

/*@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    String email;
    String password;
}*/
public record LoginRequestDTO(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
