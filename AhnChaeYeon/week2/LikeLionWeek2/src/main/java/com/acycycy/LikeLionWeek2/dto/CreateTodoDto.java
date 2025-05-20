package com.acycycy.LikeLionWeek2.dto;

import com.acycycy.LikeLionWeek2.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoDto {
    private String content;

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .content(this.content)
                .completed(false)    // 생성시 항상 미완료
                .build();
    }
}