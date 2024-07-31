package com.ivandroid.todo_list_backend.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //Buscar titulo existente
    Boolean existsByTitleIgnoreCase(String title);
}
