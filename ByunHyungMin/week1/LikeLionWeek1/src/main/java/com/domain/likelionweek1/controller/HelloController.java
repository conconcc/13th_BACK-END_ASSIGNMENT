package com.domain.likelionweek1.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {


    private final List<User> users = new ArrayList<>();

    @GetMapping("/test")
    public String test(@RequestParam String name, @RequestParam Integer age) {
        users.add(new User(name, age)); // 리스트에 사용자 추가
        return "name은 " + name + "이고, 나이는 " + age;
    }

    @GetMapping("/test/{name}")
    public String test3(@PathVariable String name) {
        Optional<User> user = users.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name)) // 입력된 name과 일치하는 사용자 찾기
                .findFirst();

        return user.map(value -> String.valueOf(value.getAge())) // 나이만 반환
                .orElse("사용자를 찾을 수 없습니다.");
    }

    static class User {
        private final String name;
        private final int age;

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

