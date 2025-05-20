package com.conconcc.LikeLionWeek2.controller;

import com.conconcc.LikeLionWeek2.dto.TodoDto;
import com.conconcc.LikeLionWeek2.sevice.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")

public class TodoController {
    private final TodoService todoService;

    //post로 받은 body를 추가
    @PostMapping("")
    public ResponseEntity<TodoDto> create(@RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(todoService.createWork(todoDto));
    }
    // 수정 - url 파라미터로 받은 id와 body에 있는 detail을 이용해 id에 맞는 todo를 찾고 detail에 맞춰서 변경
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> edit(@PathVariable Long id, @RequestBody TodoDto fix) {
        return ResponseEntity.ok(todoService.editWork(id, fix));
    }
//삭제 - id에 해당하는 todo 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.deleteWork(id);
        return ResponseEntity.noContent().build();
    }
//id에 맞는 todo의 state만 변경 (todo -> done or done-> todo)
    @PatchMapping("/{id}")
    public ResponseEntity<TodoDto> changeState(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.changeState(id));
    }

}


