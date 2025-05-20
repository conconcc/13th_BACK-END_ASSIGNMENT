package com.example.week2.dto;

import com.example.week2.entity.TodoEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class createDto {
    private String whatToDo;
    private Boolean done;

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .whatToDo(whatToDo)
                .done(false)    // 자동으로 false
                .build();
    }
}
