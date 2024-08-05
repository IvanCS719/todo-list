package com.ivandroid.todo_list_backend.domain.task.validar_registro;

import com.ivandroid.todo_list_backend.domain.task.RegistroTaskDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidarFecha implements IValidarRegistro{

    @Override
    public void validar(RegistroTaskDTO datosRegistro) {
        if (datosRegistro.dueDate().isBefore(LocalDateTime.now()))
            throw new RuntimeException("La fecha no puede ser inferior a la actual.");
    }
}
