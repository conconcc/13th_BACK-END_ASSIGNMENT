package com.example.week2.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class changeDto {
    private String whatToDo;
    private Boolean done;
}
