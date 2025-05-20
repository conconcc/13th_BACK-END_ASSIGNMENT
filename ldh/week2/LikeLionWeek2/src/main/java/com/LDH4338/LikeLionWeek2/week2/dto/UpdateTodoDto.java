package com.LDH4338.LikeLionWeek2.week2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTodoDto {
    private Long id;
    private String content;
    private boolean completed;

}
