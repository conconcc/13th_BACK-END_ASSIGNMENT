package com.conconcc.LikeLionWeek2.controller;

import com.conconcc.LikeLionWeek2.dto.HelloDto;
import com.conconcc.LikeLionWeek2.sevice.HelloService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @PostMapping("/hello")
    public String add(@RequestBody HelloDto helloDto) {
        return helloService.addUser(helloDto);
    }

    @GetMapping("/hello/{name}")
    public HelloDto show(@PathVariable String name) {
        return helloService.getUser(name);
    }

    @GetMapping("/hello")
    public List<String> getAllEntities() {
        return helloService.findAllAsStringList();
    }

    @DeleteMapping("/delete/{name}")
    public void delete(@PathVariable String name) {
        helloService.deleteUser(name);
    }

    @DeleteMapping("/deletebyid/{id}")
    public void delete(@PathVariable Long id) {
        helloService.deleteUserById(id);
    }

//    @GetMapping("/users")
//    public List<String> all() {
//        return helloService.all();
//    }


//    @GetMapping("/hello")
//    public List<String> all(){
//        return (List<String>) helloService.all();
//    }

}


//    private final HelloService helloService;
//    @PostMapping("hello")
//    public String add(@RequestParam String name, @RequestParam Integer age){
//        return helloService.addUser(helloDto.getName(), helloDto.getAge());
//    }
//
//    @GetMapping("hello/{name}")
//    public ResponseEntity<?> show(@PathVariable String name){
//        return ResponseEntity.ok(helloService.getUser(name));
//    }
//    @GetMapping("hello")
//    public List<String> all(){
//        return helloService.o
//    }

