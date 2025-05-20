package com.yusungyeon.like_lion_week_1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class Controller {
    private final List<User> userList = new ArrayList<>();

    @GetMapping("/save")
    public String save(@RequestParam String name, @RequestParam int age) {
        userList.add(new User(name, age));
        return "I call you, " + name + ", you are " + age + " years old.";
    }

    @GetMapping("/getAge/{name}")
    public String getAge(@PathVariable String name) {
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return name + " is " + user.getAge() + " years old.";
            }
        }
        return "No result : (" + name+") is not found!";
    }
}

class User {
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