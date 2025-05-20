package com.jhkim0609.likelionweek3.service;

import com.jhkim0609.likelionweek3.dto.CreateDto;
import com.jhkim0609.likelionweek3.dto.UpdateDto;
import com.jhkim0609.likelionweek3.entity.TodoEntity;
import com.jhkim0609.likelionweek3.entity.UserEntity;
import com.jhkim0609.likelionweek3.repository.TodoEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoEntityRepository todoEntityRepository;
    public TodoService(TodoEntityRepository repository){
        this.todoEntityRepository = repository;
    }
    public List<TodoEntity> getAllTodos() {
        return todoEntityRepository.findAll();
    }
    public List<TodoEntity> getTodosByUsername(String username) {
        return todoEntityRepository.findTodosByUsername(username);
    }
    public List<TodoEntity> getTodosByUsernameAndCompletion(String username, boolean completed){
        return todoEntityRepository.findTodosByUsernameAndCompletetion(username, completed);
    }
    @Transactional
    public Optional<TodoEntity> completeTodo(Long id) {
        return todoEntityRepository.findById(id).map(todo -> {
            todo.setCompleted(true);
            return todoEntityRepository.save(todo);
        });
    }
    @Transactional
    public boolean deleteTodo(Long id) {
        return todoEntityRepository.findById(id).map(todo -> {
            todoEntityRepository.delete(todo);
            return true;
        }).orElse(false);
    }
    @Transactional
    public TodoEntity addingTodo(String Todo, String name){
        UserEntity user = new UserEntity();
        user.setUsername(name);
        TodoEntity todo = TodoEntity.builder().Todo(Todo).completed(false).user(user).build();
        return todoEntityRepository.save(todo);
    }

    public String addTodo(CreateDto todoDto) {
        todoEntityRepository.save(todoDto.toEntity());
        return "Todo List adding : " + todoDto.getTodo();
    }
    public String delTodo(Long id){
        todoEntityRepository.deleteById(id);
        return "Delete completed : " + id;
    }
    public String setTodo(UpdateDto todoDto) {
        TodoEntity todoEntity = todoEntityRepository.findById(todoDto.getId()).orElseThrow(() -> new IllegalAccessError(todoDto.getId() + "의 ToDo는 없습니다."));
        String lasttodo = todoEntity.getTodo();
        todoEntity.setTodo(todoDto.getTodo());
        todoEntityRepository.save(todoEntity);
        return "Change ToDo completed\n" + lasttodo + " -> " + todoEntity.getTodo();
    }
    public String setCompleted(Long id) {
        TodoEntity todoEntity = todoEntityRepository.findById(id).orElseThrow(() -> new IllegalAccessError(id + "의 ToDo는 없습니다."));
        if (todoEntity.getCompleted()) {
            todoEntity.isCompleted(false);
            return "ToDo conversion unfinished";
        } else todoEntity.isCompleted(true);
        return "Todo conversion finished";
    }
}