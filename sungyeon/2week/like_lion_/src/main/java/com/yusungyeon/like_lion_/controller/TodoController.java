package com.yusungyeon.like_lion_.controller;

import com.yusungyeon.like_lion_.entity.TodoEntity;
import com.yusungyeon.like_lion_.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    // 전체 조회
    @GetMapping
    public List<TodoEntity> getAllTodos() {
        return todoRepository.findAll();
    }

    // 하나 추가
    @PostMapping
    public TodoEntity createTodo(@RequestBody TodoEntity todo) {
        return todoRepository.save(todo);
    }

    // 수정 (완료 여부 토글 포함)
    @PutMapping("/{id}")
    public TodoEntity updateTodo(@PathVariable int id, @RequestBody TodoEntity todoRequest) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));

        todo.setContent(todoRequest.getContent());
        todo.setCompleted(todoRequest.isCompleted());
        return todoRepository.save(todo);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable int id) {
        todoRepository.deleteById(id);
    }

    // 완료 <-> 미완료 토글
    @PatchMapping("/{id}/toggle")
    public TodoEntity toggleTodo(@PathVariable int id) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));

        todo.setCompleted(!todo.isCompleted());
        return todoRepository.save(todo);
    }
}
