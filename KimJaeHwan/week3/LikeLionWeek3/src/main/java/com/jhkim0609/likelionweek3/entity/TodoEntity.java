package com.jhkim0609.likelionweek3.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "todo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    public Long getId() { return id; }
    public boolean getCompleted(){
        return this.completed;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
