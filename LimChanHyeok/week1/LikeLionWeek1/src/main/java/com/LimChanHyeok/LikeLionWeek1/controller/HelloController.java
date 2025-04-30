package com.LimChanHyeok.LikeLionWeek1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    private final List<User> userList = new ArrayList<>();

    @GetMapping("/test")
    public String addUser(@RequestParam String name, @RequestParam int age) {
        User user = new User(name, age);
        userList.add(user);
        return "사용자 추가 완료: " + name + ", " + age;
    }

    @GetMapping("/test/{name}")
    public String getUserAge(@PathVariable String name) {
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return String.valueOf(user.getAge());
            }
        }
        return ""; // 사용자를 찾을 수 없을 때는 빈 문자열을 반환
    }

    static class User {
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