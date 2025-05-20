package com.jhkim0609.likelionweek2.dto;

import com.jhkim0609.likelionweek2.entity.HelloEntity;
import lombok.*;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HelloDto {
    private String name;
    private Integer age;
    public static HelloDto fromEntity(HelloEntity entity) {
        return HelloDto.builder().name(entity.getName()).age(entity.getAge()).build();
    }
    public HelloEntity toEntity() {
        return HelloEntity.builder().name(name).age(age).build();
    }
}