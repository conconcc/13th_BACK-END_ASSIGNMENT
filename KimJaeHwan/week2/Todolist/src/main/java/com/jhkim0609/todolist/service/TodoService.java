package com.jhkim0609.todolist.service;

import com.jhkim0609.todolist.dto.CreateDto;
import com.jhkim0609.todolist.dto.UpdateDto;
import com.jhkim0609.todolist.entity.TodoEntity;
import com.jhkim0609.todolist.repository.TodoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoEntityRepository todoEntityRepository;

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