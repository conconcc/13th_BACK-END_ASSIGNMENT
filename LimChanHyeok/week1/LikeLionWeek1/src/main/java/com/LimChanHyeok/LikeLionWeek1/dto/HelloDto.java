package com.LimChanHyeok.LikeLionWeek1.dto;

import com.LimChanHyeok.LikeLionWeek1.entity.HelloEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HelloDto {
    private String name;
    private int age;

    public static HelloDto fromEntity(HelloEntity entity) {
        return new HelloDto(entity.getName(), entity.getAge());
    }

    public HelloEntity toEntity() {
        HelloEntity entity = new HelloEntity();
        return entity;
    }
}
