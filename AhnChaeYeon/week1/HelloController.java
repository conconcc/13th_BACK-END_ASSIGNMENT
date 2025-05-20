package acycycy.LikeLionWeek1.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    private List<User> userList = new ArrayList<>();

    // 1. 쿼리 파라미터로 이름과 나이 받아서 List에 저장
    @GetMapping("/test")
    public String saveUser(@RequestParam String name, @RequestParam int age) {
        userList.add(new User(name, age));
        return "Saved " + name + " with age " + age;
    }

    // 2. URL 파라미터로 이름 받아서 나이 반환
    @GetMapping("/test/{name}")
    public String getAgeByName(@PathVariable String name) {
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return String.valueOf(user.getAge());
            }
        }
        return "User not found";
    }

    // User 클래스 내부에 정의
    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }
}
