package com.acycycy.LikeLionWeek2.dto;

import com.acycycy.LikeLionWeek2.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTodoDto {
    private Long id;
    private String content;
    private Boolean completed;

    public static UpdateTodoDto fromEntity(TodoEntity e) {
        return UpdateTodoDto.builder()
                .id(e.getId())
                .content(e.getContent())
                .completed(e.isCompleted())
                .build();
    }
}