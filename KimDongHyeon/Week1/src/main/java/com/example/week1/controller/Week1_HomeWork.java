package com.example.Week1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
}
@RestController
public class Week1_HomeWork {

    ArrayList<Person> Person_List = new ArrayList<Person>();    // 리스트 생성

    // 이름과 나이를 리스트에 저장하는 부분
    @GetMapping("/save")
    public String save(@RequestParam String name, @RequestParam Integer age) {  // 이름과 나이를 입력 받음
        Person person = new Person(name, age);
        Person_List.add(person);    // 리스트에 저장

        return "name은 " + name + "이고, 나이는 " + age;
    }

    // 나이를 검색하는 부분
    @GetMapping("/search")
    public String search(@RequestParam String name) {
        for(Person person : Person_List) {  // ArrayList에서 탐색 시작
            if(person.getName().equals(name)) { // 검색한 이름과 ArrayList의 이름과 비교
                return name + "님의 나이는 " + person.getAge() + "입니다."; // 맞다면 나이를 출력한다
            }
        }
        return "해당 이름이 존재하지 않습니다."; // 해당 이름이 리스트에 존재하지 않으면 존재하지 않음을 출력
    }
}
