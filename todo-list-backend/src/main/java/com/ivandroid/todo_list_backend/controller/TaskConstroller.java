package com.ivandroid.todo_list_backend.controller;

import com.ivandroid.todo_list_backend.domain.task.RegistroTaskDTO;
import com.ivandroid.todo_list_backend.domain.task.RespuestaTaskDTO;
import com.ivandroid.todo_list_backend.domain.task.TaskRepository;
import com.ivandroid.todo_list_backend.domain.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/task")
public class TaskConstroller {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<RespuestaTaskDTO> registrar(@RequestBody @Valid RegistroTaskDTO datos,
                                                      UriComponentsBuilder uriComponentsBuilder){

        var response = taskService.registrar(datos);

        URI uri = uriComponentsBuilder.path("/task/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);

    }

    @GetMapping
    public String hola(){
        return "Hey wey";
    }
}
