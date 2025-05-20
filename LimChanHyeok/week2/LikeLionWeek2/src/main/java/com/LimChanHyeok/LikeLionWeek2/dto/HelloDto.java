package com.LimChanHyeok.LikeLionWeek2.dto;

import com.LimChanHyeok.LikeLionWeek2.entity.HelloEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HelloDto {
    private String name;
    private Integer age;

    public static HelloDto fromEntity(HelloEntity entity) {
        return HelloDto.builder()
                .name(entity.getName())
                .age(entity.getAge())
                .build();

    }

    public HelloEntity toEntity() {
        return HelloEntity.builder()
                .name(name)
                .age(age)
                .build();
    }

}
