package com.LimChanHyeok.LikeLionWeek1.controller;

import com.LimChanHyeok.LikeLionWeek1.dto.HelloDto;
import com.LimChanHyeok.LikeLionWeek1.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @PostMapping("/hello")
    public String hello() {
        return "hello";
    }
    private final List<User> userList = new ArrayList<>();

    @PostMapping("/test")
    public HelloDto addUser(@RequestBody HelloDto helloDto) {
        return helloService.addUser(helloDto);
    }

    @PostMapping("/test/{name}")
    public HelloDto getUserAge(@PathVariable String name) {
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return getUserAge(name);
            }
        }
        return null;
    }
    @GetMapping("/test")
    public List<HelloDto> getUserList() {
        return helloService.getUserList();
    }

    static public class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
//이 부분에 리스트 생성(데베느낌)