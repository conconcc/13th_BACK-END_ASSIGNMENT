package com.ul88.likelionweek2.dto;

import com.ul88.likelionweek2.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;
    private String content;
    private boolean done;

    public TodoEntity toEntity(){
        return TodoEntity.builder()
                .id(id)
                .content(content)
                .done(done)
                .build();
    }

    public static TodoDto fromEntity(TodoEntity entity){
        return TodoDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .done(entity.isDone())
                .build();
    }
}
