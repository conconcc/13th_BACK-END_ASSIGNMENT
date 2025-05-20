package kkt9253.likelionweek2.controller;

import kkt9253.likelionweek2.dto.RequestTodoDTO;
import kkt9253.likelionweek2.dto.ResponseTodoDTO;
import kkt9253.likelionweek2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody RequestTodoDTO requestTodoDTO) {

        return ResponseEntity.ok(todoService.createTodo(requestTodoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTodoDTO> getTodoById(@PathVariable Long id) {

        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponseTodoDTO>> getAllTodo() {

        return ResponseEntity.ok(todoService.getAllTodo());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(@RequestBody RequestTodoDTO requestTodoDTO, @PathVariable Long id) {

        return ResponseEntity.ok(todoService.updateTodo(requestTodoDTO, id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTodo() {

        return ResponseEntity.ok(todoService.deleteAllTodo());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Long id) {

        return ResponseEntity.ok(todoService.deleteTodoById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeTodoDone(@PathVariable Long id) {

        String response = todoService.changeTodoDone(id);

        return ResponseEntity.ok(response);
    }
}
