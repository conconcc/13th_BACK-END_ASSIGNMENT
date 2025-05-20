package com.conconcc.LikeLionWeek2.sevice;


import com.conconcc.LikeLionWeek2.dto.TodoDto;
import com.conconcc.LikeLionWeek2.entity.TodoEntity;
import com.conconcc.LikeLionWeek2.repository.TodoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoEntityRepository todoEntityRepository;

    public TodoDto createWork(TodoDto todoDto) {
        TodoEntity todoEntity= todoDto.toEntity();
        return TodoDto.fromEntity(todoEntityRepository.save(todoEntity));
    }

    public TodoDto editWork(Long id, TodoDto fix) {
        TodoEntity todoEntity=todoEntityRepository.findById(id).orElseThrow();
        todoEntity.update(fix);
        TodoEntity update=todoEntityRepository.save(todoEntity);
        return TodoDto.fromEntity(update);
    }

    public void deleteWork(Long id) {
        todoEntityRepository.deleteById(id);
    }

    public TodoDto changeState(Long id) {
        TodoEntity todoEntity=todoEntityRepository.findById(id).orElseThrow();
        todoEntity.togglestate();
        TodoEntity update=todoEntityRepository.save(todoEntity);
        return TodoDto.fromEntity(update);
    }


}
