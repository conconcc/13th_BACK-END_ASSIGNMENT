package com.LDH4338.LikeLionWeek1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HelloController {

    private final Map<String, Integer> map = new HashMap<>();

    public String addUser(String name, int age) {
        map.put(name, age);
        return "name은 " + name + " age는 " + age;
    }

    public String getUser(String name) {
        if (!map.containsKey(name)) {
            return "이름을 찾을 수 없습니다.";
        }
        return name + "의 나이는 " + map.get(name) + " 입니다.";
    }

}
