package com.jhkim0609.todolist.dto;

import com.jhkim0609.todolist.entity.TodoEntity;
import lombok.*;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateDto {
    private Long Id;
    private String Todo;
    private boolean completed;
    public static UpdateDto fromEntity(TodoEntity entity){
        return UpdateDto.builder().Id(entity.getId()).Todo(entity.getTodo()).completed(entity.getCompleted()).build();
    }
    public TodoEntity toEntity() {return TodoEntity.builder().id(Id).Todo(Todo).completed(completed).build();}
}
