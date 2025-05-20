package com.geonhui.LikeLionWeek2.controller;

import org.springframework.web.bind.annotation.*;

import com.geonhui.LikeLionWeek2.dto.TodoDto;
import com.geonhui.LikeLionWeek2.entity.TodoEntity;
import com.geonhui.LikeLionWeek2.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<String> addTodo(@Valid @RequestBody TodoDto todoDto) {
        todoService.addTodo(todoDto);

        return ResponseEntity.ok("투두가 정상적으로 등록되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<TodoEntity>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<TodoEntity> getTodo(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodo(id));
    }

    @PutMapping
    public ResponseEntity<String> updateTodo(@RequestBody TodoEntity todoEntity) {
        todoService.updateTodo(todoEntity);

        return ResponseEntity.ok("투두 수정이 완료되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.removeTodo(id);
        
        return ResponseEntity.ok("투두 삭제가 완료되었습니다.");
    }
}
