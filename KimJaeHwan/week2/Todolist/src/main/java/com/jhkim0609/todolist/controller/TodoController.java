package com.jhkim0609.todolist.controller;

import com.jhkim0609.todolist.dto.CreateDto;
import com.jhkim0609.todolist.dto.UpdateDto;
import com.jhkim0609.todolist.entity.TodoEntity;
import com.jhkim0609.todolist.repository.TodoEntityRepository;
import com.jhkim0609.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoEntityRepository todoEntityRepository;
    private final TodoService todoService;
    @GetMapping("Todo")
    public List<TodoEntity> getAllTodo(){
        return todoEntityRepository.findAll();
    }
    @PostMapping("Todo")
    public String add(@RequestBody CreateDto todoDto) {
        return todoService.addTodo(todoDto);
    }
    @PatchMapping("Todo/{id}")
    public String updateTodo(@RequestBody UpdateDto todoDto){
        return todoService.setTodo(todoDto);
    }
    @PutMapping("Todo/{id}")
    public String updateCompleted(@PathVariable Long id){
        return todoService.setCompleted(id);
    }
    @DeleteMapping("Todo/{id}")
    public String deleteTodo(@PathVariable Long id){
        return todoService.delTodo(id);
    }
}
