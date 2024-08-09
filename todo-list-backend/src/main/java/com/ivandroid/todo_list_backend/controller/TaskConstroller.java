package com.ivandroid.todo_list_backend.controller;

import com.ivandroid.todo_list_backend.domain.task.RegistroTaskDTO;
import com.ivandroid.todo_list_backend.domain.task.RespuestaTaskDTO;
import com.ivandroid.todo_list_backend.domain.task.Task;
import com.ivandroid.todo_list_backend.domain.task.TaskRepository;
import com.ivandroid.todo_list_backend.domain.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskConstroller {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    //Endpoint para registrar una tarea
    @PostMapping
    public ResponseEntity<RespuestaTaskDTO> registrar(@RequestBody @Valid RegistroTaskDTO datos,
                                                      UriComponentsBuilder uriComponentsBuilder){

        var response = taskService.registrar(datos);

        URI uri = uriComponentsBuilder.path("/task/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);

    }

    //Endpoint para mostrar toda las tareas
    @GetMapping
    public ResponseEntity<Page<RespuestaTaskDTO>> obtenerTodo(@PageableDefault(size = 10)Pageable paginacion){

        var listaTaks = taskRepository.findAllByOrderByDueDate(paginacion)
                .map(RespuestaTaskDTO::new);

        return ResponseEntity.ok(listaTaks);
    }

    //Endpoint para mostrar una tarea
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTaskDTO> obtenerUnaTarea(@PathVariable Long id){
        var task = taskRepository.findById(id);

        if(task.isPresent()){
            var datosTask = new RespuestaTaskDTO(task.get());
            return ResponseEntity.ok(datosTask);
        }

        throw new RuntimeException("La tarea no fue encontrada");
    }
}
