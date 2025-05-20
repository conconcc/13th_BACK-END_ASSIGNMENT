package hugestep.likelion_week_1.controller;

import hugestep.likelion_week_1.dto.CreateDto;
import hugestep.likelion_week_1.dto.UpdateDto;
import hugestep.likelion_week_1.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @GetMapping("/todolist")
    public List<UpdateDto> showAllUser() {
        return helloService.showAll();
    }

    @PostMapping("/todolist")
    public String add(@RequestBody CreateDto helloDto) {
        return helloService.addToDo(helloDto);
    }

    @DeleteMapping("/todolist/{id}")
    public String delete(@PathVariable Long id) {
        return helloService.deleteToDo(id);
    }

    @PutMapping("/todolist")
    public String update(@RequestBody UpdateDto updateDto) {
        return helloService.updateToDo(updateDto);
    }

    @PatchMapping("/todolist/{id}")
    public String toggle(@PathVariable Long id) {
        return helloService.changeCompleteState(id);
    }

}
