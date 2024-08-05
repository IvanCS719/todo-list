package com.ivandroid.todo_list_backend.domain.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //Buscar titulo existente
    Boolean existsByTitleIgnoreCase(String title);

    Page<Task> findAllByOrderByDueDate(Pageable paginacion);
}
