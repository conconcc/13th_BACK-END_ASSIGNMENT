package com.jhkim0609.likelionweek2.controller;

import com.jhkim0609.likelionweek2.service.HelloService;
import com.jhkim0609.likelionweek2.dto.HelloDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
