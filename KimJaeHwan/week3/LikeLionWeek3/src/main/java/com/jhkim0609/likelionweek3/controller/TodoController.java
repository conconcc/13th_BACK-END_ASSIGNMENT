package com.jhkim0609.likelionweek3.controller;

import com.jhkim0609.likelionweek3.dto.CreateDto;
import com.jhkim0609.likelionweek3.dto.UpdateDto;
import com.jhkim0609.likelionweek3.dto.UserDto;
import com.jhkim0609.likelionweek3.entity.TodoEntity;
import com.jhkim0609.likelionweek3.repository.TodoEntityRepository;
import com.jhkim0609.likelionweek3.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoEntityRepository todoEntityRepository;
    private final TodoService todoService;

    @GetMapping("/user/{name}")
    public List<CreateDto> getTodosByUsername(@PathVariable String name) {
        return todoService.getTodosByUsername(name).stream().map(CreateDto::fromEntity).toList();
    }
    @GetMapping("/user")
    public List<CreateDto> getTodosByUsernameAddCompletion(@RequestBody UserDto userDto) {
        return todoService.getTodosByUsernameAndCompletion(userDto.getName(), userDto.isCompleted()).stream().map(CreateDto::fromEntity).toList();
    }

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
