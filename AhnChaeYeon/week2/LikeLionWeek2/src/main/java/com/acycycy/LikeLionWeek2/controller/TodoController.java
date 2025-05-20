package com.acycycy.LikeLionWeek2.controller;

import com.acycycy.LikeLionWeek2.dto.CreateTodoDto;
import com.acycycy.LikeLionWeek2.dto.UpdateTodoDto;
import com.acycycy.LikeLionWeek2.entity.TodoEntity;
import com.acycycy.LikeLionWeek2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<UpdateTodoDto>> findAll() {
        List<UpdateTodoDto> list = todoService.findAll().stream()
                .map(UpdateTodoDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<UpdateTodoDto> create(@RequestBody CreateTodoDto dto) {
        TodoEntity saved = todoService.create(dto.toEntity());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UpdateTodoDto.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateTodoDto> update(
            @PathVariable Long id,
            @RequestBody UpdateTodoDto dto
    ) {
        TodoEntity updated = todoService.update(id, dto);
        return ResponseEntity.ok(UpdateTodoDto.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateTodoDto> changeStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> body
    ) {
        boolean completed = body.getOrDefault("completed", false);
        TodoEntity changed = todoService.changeStatus(id, completed);
        return ResponseEntity.ok(UpdateTodoDto.fromEntity(changed));
    }
}
