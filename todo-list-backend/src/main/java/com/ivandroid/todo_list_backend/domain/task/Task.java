package com.ivandroid.todo_list_backend.domain.task;

import com.ivandroid.todo_list_backend.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public void actualiar(ActualizarTaskDTO datos){
        if(datos.title() != null) this.title = datos.title();
        if(datos.description() != null) this.description = datos.description();
        if(datos.dueDate() != null) this.dueDate = datos.dueDate();
    }
}
