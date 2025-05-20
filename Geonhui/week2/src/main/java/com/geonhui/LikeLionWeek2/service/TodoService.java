package com.geonhui.LikeLionWeek2.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.geonhui.LikeLionWeek2.dto.TodoDto;
import com.geonhui.LikeLionWeek2.entity.TodoEntity;
import com.geonhui.LikeLionWeek2.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    
    private final TodoRepository todoRepository;

    public void addTodo(TodoDto todoDto) {
        TodoEntity todo = TodoEntity.builder()
                                    .title(todoDto.getTitle())
                                    .isComplete(todoDto.isComplete())
                                    .build();

        todoRepository.save(todo);
    }

    public List<TodoEntity> getAllTodos() {
        return todoRepository.findAll();
    }

    public TodoEntity getTodo(Long id) {
        TodoEntity todo = todoRepository.findById(id)
                            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 투두"));

        return todo;
    }

    public void removeTodo(Long id) {
        TodoEntity todo = todoRepository.findById(id)
                            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 투두"));

        todoRepository.deleteById(id);
    }

    public void updateTodo(TodoEntity todoEntity) {
        TodoEntity prevTodo = todoRepository.findById(todoEntity.getId())
        .orElseThrow(() -> new NoSuchElementException("존재하지 않는 투두"));

        todoRepository.save(todoEntity);
    }
}
