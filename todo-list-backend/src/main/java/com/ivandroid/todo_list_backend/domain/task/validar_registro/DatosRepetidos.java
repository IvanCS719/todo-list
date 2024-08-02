package com.ivandroid.todo_list_backend.domain.task.validar_registro;

import com.ivandroid.todo_list_backend.domain.task.RegistroTaskDTO;
import com.ivandroid.todo_list_backend.domain.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatosRepetidos implements IValidarRegistro{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void validar(RegistroTaskDTO datosRegistro) {
        //Buscar titulo existente
        var tituloRepetido = taskRepository.existsByTitleIgnoreCase(datosRegistro.title());

        if (tituloRepetido) throw new RuntimeException("Título de la tarea duplicado");

    }
}
