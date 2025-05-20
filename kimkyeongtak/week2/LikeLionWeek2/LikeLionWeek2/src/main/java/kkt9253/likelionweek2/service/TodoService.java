package kkt9253.likelionweek2.service;

import kkt9253.likelionweek2.dto.RequestTodoDTO;
import kkt9253.likelionweek2.dto.ResponseTodoDTO;
import kkt9253.likelionweek2.entity.Todo;
import kkt9253.likelionweek2.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public String createTodo(RequestTodoDTO requestTodoDTO) {

        todoRepository.save(Todo.builder()
                .content(requestTodoDTO.getContent())
                .build());

        return "success create Todo";
    }

    public ResponseTodoDTO getTodoById(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Todo가 존재하지 않습니다."));

        return ResponseTodoDTO.builder()
                .id(todo.getId())
                .content(todo.getContent())
                .isDone(todo.isDone())
                .build();
    }

    public String updateTodo(RequestTodoDTO requestTodoDTO, Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Todo가 존재하지 않습니다."));

        todo.setContent(requestTodoDTO.getContent());
        todoRepository.save(todo);

        return "success update Todo";
    }

    public String deleteAllTodo() {

        todoRepository.deleteAll();

        return "success delete all Todo";
    }

    public String deleteTodoById(Long id) {

        if (!todoRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 id의 Todo가 존재하지 않습니다.");
        }

        todoRepository.deleteById(id);

        return "success delete Todo";
    }

    public String changeTodoDone(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Todo가 존재하지 않습니다."));

        todo.changeIsDone();
        todoRepository.save(todo);

        return "success change Todo isDone";
    }

    public List<ResponseTodoDTO> getAllTodo() {

        List<ResponseTodoDTO> responseTodoDTOS = todoRepository.findAll().stream()
                .map(ResponseTodoDTO::fromTodo)
                .toList();
        return responseTodoDTOS;
    }
}
