package com.yusungyeon.like_lion_.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoDto {
    private String id;
    private String content;
    private boolean completed;
}
