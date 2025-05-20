package com.jhkim0609.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable=false)
    private String Todo;
    @Column(name = "Completed")
    private boolean completed;
    public void setTodo(String Todo){
        this.Todo = Todo;
    }
    public void isCompleted(boolean status){
        this.completed = status;
    }
    public Long getId() {
        return id;
    }
    public boolean getCompleted(){
        return this.completed;
    }
}
