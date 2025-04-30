package com.ul88.likelionweek1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping
    public String home(){
        return "과제 열심히 하세요~~";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/test")
    public String test(@RequestParam String name){
        return "name은 "+ name;
    }

    @GetMapping("/test2")
    public String test2(@RequestParam String name, @RequestParam Integer age){
        return "name은 " + name + "이고, 나이는 " + age;
    }

    @GetMapping("/test3/{name}")
    public String test3(@PathVariable String name){
        return "name은 " + name;
    }
}
