package com.domain.likelionweek2.service;

import com.domain.likelionweek2.dto.TodoDto;
import com.domain.likelionweek2.entity.TodoEntity;
import com.domain.likelionweek2.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public TodoDto addTodo(TodoDto dto) {
        TodoEntity entity = dto.toEntity();
        entity.setStatus(TodoEntity.Status.TODO);
        return TodoDto.fromEntity(repository.save(entity));
    }

    public List<TodoDto> getAllTodos() {
        return repository.findAll().stream()
                .map(TodoDto::fromEntity)
                .toList();
    }

    public TodoDto updateTodo(Long id, String newContent) {
        TodoEntity todo = repository.findById(id).orElseThrow();
        todo.setContent(newContent);
        return TodoDto.fromEntity(repository.save(todo));
    }

    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }

    public TodoDto markAsDone(Long id) {
        TodoEntity todo = repository.findById(id).orElseThrow();
        todo.setStatus(TodoEntity.Status.DONE);
        return TodoDto.fromEntity(repository.save(todo));
    }

    public TodoDto markAsTodo(Long id) {
        TodoEntity todo = repository.findById(id).orElseThrow();
        todo.setStatus(TodoEntity.Status.TODO);
        return TodoDto.fromEntity(repository.save(todo));
    }
}
