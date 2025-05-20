package com.LDH4338.LikeLionWeek2.week2.service;

import com.LDH4338.LikeLionWeek2.week2.dto.TodoDto;
import com.LDH4338.LikeLionWeek2.week2.dto.UpdateTodoDto;
import com.LDH4338.LikeLionWeek2.week2.entity.Todo;
import com.LDH4338.LikeLionWeek2.week2.repository.TodoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    //할 일 추가
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = todoRepository.save(todoDto.toEntity());
        return TodoDto.fromEntity(todo);
    }

    //전체 할 일 조회
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    //할 일 수정
    public TodoDto updateTodo(UpdateTodoDto todoDto) {
        Todo todo = todoRepository.findById(todoDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다."));

        todo.changeContent(todoDto.getContent());

        return  TodoDto.fromEntity(todoRepository.save(todo));
    }

    //할 일 삭제
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    //상태 변경 할 일 (미완 <-> 완료)
    public TodoDto changedCompleted(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다."));

        todo.toggleCompleted(); //toggleCompleted를 통해 Setter를 이용해 상태 변경을 하는 것이 아닌
        return TodoDto.fromEntity(todoRepository.save(todo));
    }
}
