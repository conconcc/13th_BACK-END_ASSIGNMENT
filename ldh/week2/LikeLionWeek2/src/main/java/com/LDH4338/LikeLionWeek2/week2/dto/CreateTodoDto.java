package com.LDH4338.LikeLionWeek2.week2.dto;

import com.LDH4338.LikeLionWeek2.week2.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateTodoDto {
    private String content;

    public Todo toEntity() {
        return new Todo(null, content, false);
    }
}
