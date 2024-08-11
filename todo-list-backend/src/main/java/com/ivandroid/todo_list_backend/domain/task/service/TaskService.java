package com.ivandroid.todo_list_backend.domain.task.service;

import com.ivandroid.todo_list_backend.domain.task.*;
import com.ivandroid.todo_list_backend.domain.task.validar_registro.IValidarRegistro;
import com.ivandroid.todo_list_backend.domain.user.User;
import com.ivandroid.todo_list_backend.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private List<IValidarRegistro> validarRegistroList;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public RespuestaTaskDTO registrar( RegistroTaskDTO datos ) {
        //Se validan los datos
       validarRegistroList.forEach(v -> v.validar(datos));

       //Se verifica si el usuario existe
        User user = buscarUser(datos.userId());

        //Se crea una nuevo entidad de task
        Task task = new Task(null, datos.title(), datos.description(), datos.dueDate(),
                false, user);

        //Se guarda la entidad en la base de datos
        taskRepository.save(task);

        //Se retorna los datos de la entidad guardada
        return new RespuestaTaskDTO(task);

    }

    public RespuestaTaskDTO actualizar(ActualizarTaskDTO datos, Long id){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            validarRegistroList.forEach(v -> v.validar(new RegistroTaskDTO(datos)));
            task.get().actualiar(datos);
            return new RespuestaTaskDTO(task.get());
        }
        throw new RuntimeException("La tarea no fue encontrada.");
    }

    public void eliminar(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada."));

        User user = task.getUser();
        if (user != null) {
            user.getTasks().remove(task); // Elimina la tarea de la lista en memoria
        }

        taskRepository.delete(task);
    }

    private User buscarUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) return user.get();
        throw new RuntimeException("El usuario no existe");
    }
}
