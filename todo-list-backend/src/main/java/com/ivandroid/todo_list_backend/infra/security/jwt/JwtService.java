package com.ivandroid.todo_list_backend.infra.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ivandroid.todo_list_backend.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String getToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("TodoList by IvanDroid")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generarFecha())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Instant generarFecha() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject(String token){
        if (token==null){
            throw new RuntimeException("El token no puede estar vácio");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("TodoList by IvanDroid")
                    // reusable verifier instance
                    .build()
                    .verify(token);
            return verifier.getSubject();
        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            System.out.println(exception.getMessage());
            throw new RuntimeException("Token no válido: " +exception.getMessage());
        }
    }
}
