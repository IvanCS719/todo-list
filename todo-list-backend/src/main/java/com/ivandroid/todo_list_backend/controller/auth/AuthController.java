package com.ivandroid.todo_list_backend.controller.auth;

import com.ivandroid.todo_list_backend.infra.security.jwt.RespuestaJwtDTO;
import com.ivandroid.todo_list_backend.domain.user.LoginRequestDTO;
import com.ivandroid.todo_list_backend.domain.user.RegisterRequestDTO;
import com.ivandroid.todo_list_backend.infra.security.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<RespuestaJwtDTO> login(@RequestBody @Valid LoginRequestDTO request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RespuestaJwtDTO> register(@RequestBody @Valid RegisterRequestDTO request){
        return ResponseEntity.ok(authService.register(request));
    }
}
