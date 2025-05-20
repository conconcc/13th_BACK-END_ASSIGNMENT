package com.LDH4338.LikeLionWeek2.week2.dto;

import com.LDH4338.LikeLionWeek2.week2.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;
    private String content;
    private boolean completed;

    // 기존 생성자 제거 or private으로 변경 가능
    private TodoDto(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.completed = todo.isCompleted();
    }

    //fromEntity 추가 -> 행위의 명확성을 위해
    public static TodoDto fromEntity(Todo todo) {
        return new TodoDto(todo);
    }

    //toEntity()를 기존 new 생성자와 setter를 이용해 가져오던 방식을 builder로 교체
    /*Setter를 지양하는 이유는
    * 1. 객체 내부 상태가 외부에 무분별하게 변경 될 수 있어서
    * 2. 비지니스 로직이 의도치 않게 깨지거나, 디버깅이 어려워짐
    * 3. 책임이 분산되어 객체지향 원칙 중 캡슐화가 약해짐*/
    public Todo toEntity() {
        return Todo.builder()
                .content(this.content)
                .completed(this.completed)
                .build();
    }
}
