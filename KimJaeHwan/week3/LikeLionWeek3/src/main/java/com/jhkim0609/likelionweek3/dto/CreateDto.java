package com.jhkim0609.likelionweek3.dto;

import com.jhkim0609.likelionweek3.entity.TodoEntity;
import lombok.*;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateDto {
    private String Todo;
    private boolean completed;
    public static CreateDto fromEntity(TodoEntity entity) {
        return CreateDto.builder().Todo(entity.getTodo()).completed(entity.getCompleted()).build();
    }
    public TodoEntity toEntity() {
        return TodoEntity.builder().Todo(Todo).completed(completed).build();
    }
}