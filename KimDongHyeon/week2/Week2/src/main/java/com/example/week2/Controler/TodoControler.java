package com.example.week2.Controler;

import com.example.week2.dto.*;
import com.example.week2.entity.TodoEntity;
import com.example.week2.repository.TodoEntityRepository;
import com.example.week2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoControler {
    private final TodoService todoService;
    private final TodoEntityRepository todoEntityRepository;

    // Restful하게 수정
    @PostMapping("ToDo")
    public String add(@RequestBody createDto todoDto) { return todoService.addTodo(todoDto); }

    @GetMapping("ToDo") // 모든 일정 확인
    public List<TodoEntity> getAllTodo() {  // 모든 투두리스트를 확인하기
        return todoEntityRepository.findAll();
    }

    // 한번에 할일과 완료여부를 변경할 수 있도록 변경
    @PatchMapping("ToDo/{id}")    // 투두리스트 수정하기
    public String update(@PathVariable Long id, @RequestBody changeDto todoDto) {
        return todoService.changeToDo(id, todoDto);
    }

    @DeleteMapping("ToDo/{id}")   // 투두리스트 삭제하기
    public String delete(@PathVariable Long id) {
        return todoService.delete(id);
    }
}
