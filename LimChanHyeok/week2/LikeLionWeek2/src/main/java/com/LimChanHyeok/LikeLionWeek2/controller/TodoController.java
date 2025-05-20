package com.LimChanHyeok.LikeLionWeek2.controller;

import com.LimChanHyeok.LikeLionWeek2.dto.TodoDto;
import com.LimChanHyeok.LikeLionWeek2.dto.UserDto;
import com.LimChanHyeok.LikeLionWeek2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    //추가
    @PostMapping
    public ResponseEntity<String> add(@RequestBody TodoDto dto){
        return ResponseEntity.ok(todoService.addTodo(dto));
    }

    //수정
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody TodoDto dto){
        return ResponseEntity.ok(todoService.updateTodo(id,dto));
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(todoService.deleteTodo(id));
    }

    //할 일 -> 완료
//    @PatchMapping("/{id}/complete")
//    public ResponseEntity<String> complete(@PathVariable Long id){
//        return ResponseEntity.ok(todoService.markAsCompleted(id));
//    }
//
//    //완료 -> 할 일
//    @PatchMapping("/{id}/undo")
//    public ResponseEntity<String> undo(@PathVariable Long id){
//        return ResponseEntity.ok(todoService.markAsUncompleted(id));
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> toggleComplete(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.toggleCompleted(id));
    }

    @GetMapping("/user/{username}")
     public List<TodoDto> getTodosByUsername(@PathVariable String username){
        return todoService.getTodosByUsername(username).stream()
                .map(TodoDto::fromEntitiy)
                .toList();
    }

    @GetMapping("/user")
    public List<TodoDto> getTodosByUsernameAndCompletion(@RequestBody UserDto userDto){
                return todoService.getTodosByUsernameAndCompleted(userDto.getUsername(),
                        userDto.isCompleted()).stream()
                        .map(TodoDto::fromEntitiy)
                        .toList();
    }




}
