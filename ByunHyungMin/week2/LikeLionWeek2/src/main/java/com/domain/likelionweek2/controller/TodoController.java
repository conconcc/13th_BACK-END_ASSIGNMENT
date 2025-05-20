package com.domain.likelionweek2.controller;

import com.domain.likelionweek2.dto.TodoDto;
import com.domain.likelionweek2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto dto) {
        return ResponseEntity.ok(todoService.addTodo(dto));
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto dto) {
        return ResponseEntity.ok(todoService.updateTodo(id, dto.getContent()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> markAsDone(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.markAsDone(id));
    }

    @PatchMapping("/{id}/undo")
    public ResponseEntity<TodoDto> markAsTodo(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.markAsTodo(id));
    }
}
