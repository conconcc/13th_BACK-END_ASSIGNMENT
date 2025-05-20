package com.LDH4338.LikeLionWeek2.week2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private boolean completed = false; // 할 일 추가시 기본값은 false

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeCompleted(boolean completed) {
        this.completed = completed;
    }

    public void addContent(String content) {
        this.content = content;
    }

    public void toggleCompleted() {
        this.completed = !this.completed;
    }

}
