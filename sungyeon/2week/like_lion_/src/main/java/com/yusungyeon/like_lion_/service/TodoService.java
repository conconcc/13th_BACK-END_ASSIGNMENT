package com.yusungyeon.like_lion_.service;

import com.yusungyeon.like_lion_.entity.TodoEntity;
import com.yusungyeon.like_lion_.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 전체 할 일 조회
    public List<TodoEntity> getAllTodos() {
        return todoRepository.findAll();
    }

    // 할 일 추가
    public TodoEntity addTodo(String content) {
        TodoEntity todo = new TodoEntity();
        todo.setContent(content);
        todo.setCompleted(false); // 처음엔 완료 안 됨
        return todoRepository.save(todo);
    }

    // 할 일 수정
    public TodoEntity updateTodoContent(Integer id, String newContent) {
        TodoEntity todo = findTodoById(id);
        todo.setContent(newContent);
        return todoRepository.save(todo);
    }

    // 완료/미완료 토글
    public TodoEntity toggleTodo(Integer id) {
        TodoEntity todo = findTodoById(id);
        todo.setCompleted(!todo.isCompleted()); // 완료 상태 반대로 바꾸기
        return todoRepository.save(todo);
    }

    // 할 일 삭제
    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);
    }

    // (내부용) id로 할 일 찾기
    private TodoEntity findTodoById(Integer id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));
    }
}
