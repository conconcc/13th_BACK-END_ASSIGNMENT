package com.acycycy.LikeLionWeek2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {
    private String content;
    private boolean completed;

}
