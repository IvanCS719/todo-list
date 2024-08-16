package com.ivandroid.todo_list_backend.controller.auth;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public String login(){
        return "Te has logueado";
    }

    @PostMapping("/register")
    public String register(){
        return "Te has registrado";
    }
}
