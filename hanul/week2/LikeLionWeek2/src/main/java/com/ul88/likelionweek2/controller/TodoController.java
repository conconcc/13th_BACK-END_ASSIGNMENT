package com.ul88.likelionweek2.controller;

import com.ul88.likelionweek2.dto.RequestAddTodoDto;
import com.ul88.likelionweek2.dto.RequestUpdateTodoDto;
import com.ul88.likelionweek2.dto.TodoDto;
import com.ul88.likelionweek2.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addTodo(@RequestBody RequestAddTodoDto requestAddTodoDto){
        return ResponseEntity.ok(todoService.add(requestAddTodoDto.getContent()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeTodo(@PathVariable Long id){
        return ResponseEntity.ok(todoService.change(id));
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody RequestUpdateTodoDto requestUpdateTodoDto){
        return ResponseEntity.ok(todoService.update(requestUpdateTodoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(todoService.delete(id));
    }
}
