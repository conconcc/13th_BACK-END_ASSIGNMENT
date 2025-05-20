package kkt9253.likelionweek2.dto;

import kkt9253.likelionweek2.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseTodoDTO {

    private Long id;
    private String content;
    private boolean isDone;

    public static ResponseTodoDTO fromTodo(Todo todo) {
        return ResponseTodoDTO.builder()
                .id(todo.getId())
                .content(todo.getContent())
                .isDone(todo.isDone())
                .build();
    }
}
