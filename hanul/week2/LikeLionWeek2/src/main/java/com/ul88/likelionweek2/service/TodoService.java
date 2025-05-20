package com.ul88.likelionweek2.service;

import com.ul88.likelionweek2.dto.RequestUpdateTodoDto;
import com.ul88.likelionweek2.dto.TodoDto;
import com.ul88.likelionweek2.entity.TodoEntity;
import com.ul88.likelionweek2.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<TodoDto> findAll(){
        return todoRepository.findAll().stream().map(TodoDto::fromEntity).toList();
    }

    public TodoDto add(String content){
        return TodoDto.fromEntity(
                todoRepository.save(TodoEntity.builder()
                        .content(content)
                        .build())
        );
    }

    public TodoDto change(Long id){
        TodoEntity todoEntity = todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당하는 ID가 없습니다."));

        todoEntity.changeDone();

        todoRepository.flush();
        return TodoDto.fromEntity(todoEntity);
    }

    public TodoDto update(RequestUpdateTodoDto requestUpdateTodoDto){
        TodoEntity todoEntity = todoRepository.findById(requestUpdateTodoDto.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당하는 ID가 없습니다."));

        todoEntity.changeContent(requestUpdateTodoDto.getContent());

        todoRepository.flush();
        return TodoDto.fromEntity(todoEntity);
    }

    public TodoDto delete(Long id){
        TodoEntity todoEntity = todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당하는 ID가 없습니다."));
        TodoDto ret = TodoDto.fromEntity(todoEntity);
        todoRepository.delete(todoEntity);
        return ret;
    }

}
