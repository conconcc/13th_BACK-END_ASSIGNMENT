package com.acycycy.LikeLionWeek2.service;

import com.acycycy.LikeLionWeek2.dto.UpdateTodoDto;
import com.acycycy.LikeLionWeek2.entity.TodoEntity;
import com.acycycy.LikeLionWeek2.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoEntity> findAll() {
        return todoRepository.findAll();
    }

    public TodoEntity create(TodoEntity toSave) {
        return todoRepository.save(toSave);
    }

    public TodoEntity update(Long id, UpdateTodoDto dto) {
        TodoEntity existing = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID[" + id + "] 할 일을 찾을 수 없습니다."));
        existing.setContent(dto.getContent());
        existing.setCompleted(dto.getCompleted());
        return todoRepository.save(existing);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    public TodoEntity changeStatus(Long id, boolean completed) {
        TodoEntity existing = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID[" + id + "] 할 일을 찾을 수 없습니다."));
        existing.setCompleted(completed);
        return todoRepository.save(existing);
    }
}