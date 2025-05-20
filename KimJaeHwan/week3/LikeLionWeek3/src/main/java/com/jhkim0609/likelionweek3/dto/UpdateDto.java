package com.jhkim0609.likelionweek3.dto;

import com.jhkim0609.likelionweek3.entity.TodoEntity;
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
    private String name;
    public static UpdateDto fromEntity(TodoEntity entity){
        return UpdateDto.builder()
                .Id(entity.getId())
                .Todo(entity.getTodo())
                .completed(entity.getCompleted())
                .name(entity.getUser() != null ? entity.getUser().getUsername() : null)
                .build();
    }
    public TodoEntity toEntity() {return TodoEntity.builder().id(Id).Todo(Todo).completed(completed).build();}
}
