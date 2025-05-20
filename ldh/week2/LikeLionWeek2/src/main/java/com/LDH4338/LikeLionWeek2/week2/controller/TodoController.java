package com.LDH4338.LikeLionWeek2.week2.controller;

import com.LDH4338.LikeLionWeek2.week2.dto.TodoDto;
import com.LDH4338.LikeLionWeek2.week2.dto.UpdateTodoDto;
import com.LDH4338.LikeLionWeek2.week2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(todoService.createTodo(todoDto));
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody UpdateTodoDto updateTodoDto) {
        return ResponseEntity.ok(todoService.updateTodo(updateTodoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoDto> toggleCompleted(@PathVariable("id") Long id) {
        TodoDto updated = todoService.changedCompleted(id);
        return ResponseEntity.ok(updated);
    }
}
