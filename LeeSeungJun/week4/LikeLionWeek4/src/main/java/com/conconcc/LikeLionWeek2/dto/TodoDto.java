package com.conconcc.LikeLionWeek2.dto;

import com.conconcc.LikeLionWeek2.entity.TodoEntity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TodoDto {
    private String title;
    private boolean state;

    public TodoEntity toEntity(){
        return TodoEntity.builder()
                .title(title)
                .state(state)
                .build();
    }

    public static TodoDto fromEntity(TodoEntity entity){
        return TodoDto.builder()
                .title(entity.getTitle())
                .state(entity.isState())
                .build();
    }


}
