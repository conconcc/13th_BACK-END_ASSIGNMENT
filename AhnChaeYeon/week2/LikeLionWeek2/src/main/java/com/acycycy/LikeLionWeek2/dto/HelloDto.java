package com.acycycy.LikeLionWeek2.dto;

import com.acycycy.LikeLionWeek2.entity.HelloEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HelloDto {
    private String name;
    private Integer age;

    public static HelloDto from(HelloEntity entity) {
        return HelloDto.builder().name(entity.getName())
                .age(entity.getAge())
                .build();
    }

    public HelloEntity toEntity() {
        return HelloEntity.builder()
                .name(name).
                age(age).
                build();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
