package com.domain.likelionweek2.dto;

import com.domain.likelionweek2.entity.TodoEntity;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class TodoDto {
    private String content;
    private String status; // TODO or DONE

    public static TodoDto fromEntity(TodoEntity entity){
        return TodoDto.builder()
                .content(entity.getContent())
                .status(entity.getStatus().name())
                .build();
    }

    public TodoEntity toEntity(){
        return TodoEntity.builder()
                .content(content)
                .status(status == null ? TodoEntity.Status.TODO : TodoEntity.Status.valueOf(status))
                .build();
    }
}
