package com.LimChanHyeok.LikeLionWeek2.controller;

import com.LimChanHyeok.LikeLionWeek2.dto.HelloDto;
import com.LimChanHyeok.LikeLionWeek2.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @PostMapping("hello")
    public String add(@RequestBody HelloDto helloDto) {
        return helloService.addUser(helloDto);
    }

    @GetMapping("hello/{name}")
    public ResponseEntity<?> show(@PathVariable String name) {
        return ResponseEntity.ok(helloService.getUser(name));
    }

    @GetMapping("/hello")
    public ResponseEntity<List<HelloDto>> showAll() {
        List<HelloDto> helloDtoList = helloService.findAll();
        return ResponseEntity.ok(helloDtoList);
    }

    @DeleteMapping("/hello")
    public ResponseEntity<String> deleteAll(){
        String message = helloService.deleteAllUsers();
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/hello/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        String message = helloService.deleteById(id);
        return ResponseEntity.ok(message);
    }
}

