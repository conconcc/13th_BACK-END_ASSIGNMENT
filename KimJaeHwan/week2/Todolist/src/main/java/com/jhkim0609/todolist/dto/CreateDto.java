package com.jhkim0609.todolist.dto;

import com.jhkim0609.todolist.entity.TodoEntity;
import lombok.*;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateDto {
    private String Todo;
    private boolean completed;
    public static CreateDto fromEntity(TodoEntity entity){
        return CreateDto.builder().Todo(entity.getTodo()).completed(entity.getCompleted()).build();
    }
    public TodoEntity toEntity() {
        return TodoEntity.builder().Todo(Todo).completed(completed).build();
    }
}