package com.ivandroid.todo_list_backend.infra.security;

import com.ivandroid.todo_list_backend.domain.user.*;
import com.ivandroid.todo_list_backend.domain.user.RegisterRequestDTO;
import com.ivandroid.todo_list_backend.infra.security.jwt.JwtService;
import com.ivandroid.todo_list_backend.infra.security.jwt.RespuestaJwtDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public RespuestaJwtDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        ));

        var user = userRepository.findByEmail(request.email()).orElseThrow();
        String token = jwtService.getToken(user);
        return new RespuestaJwtDTO(token);
    }
    public RespuestaJwtDTO register(RegisterRequestDTO request) {
        User user = new User(
                null,
                request.username(),
                request.email(),
                passwordEncoder.encode(request.password()),
                new ArrayList<>()
        );
        userRepository.save(user);

        return new RespuestaJwtDTO(jwtService.getToken(user));
    }
}
