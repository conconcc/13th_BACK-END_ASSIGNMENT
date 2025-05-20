package com.ul88.likelionweek1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //이걸 쓰면 GetMapping을 사용할 수 있음
public class HelloController {

    private List<User> userList = new ArrayList<>();

    @GetMapping("/test")
    public String test(@RequestParam String name, @RequestParam Integer age) {
        User user = new User(name, age);
        userList.add(user);
        return "name = " + name + ", age = " + age;
    }

    @GetMapping("/test/{name}")
    public String getUserAge(@PathVariable String name) {
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return "name = " + name + ", age = " + user.getAge();
            }
        }
        return "User not found";
    }
}

class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public Integer getAge() { return age; }

    @Override
    public String toString() {
        return "name = " + name + ", age = " + age;
    }
}
