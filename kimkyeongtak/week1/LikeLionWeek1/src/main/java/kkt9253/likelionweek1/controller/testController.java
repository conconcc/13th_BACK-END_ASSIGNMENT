package kkt9253.likelionweek1.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class testController {

    @Getter
    @AllArgsConstructor
    static class Person {
        private String name;
        private int age;
    }

    private final List<Person> personList = new ArrayList<>();

    // 1. 쿼리 파라미터로 이름과 나이 입력받아 List에 저장
    @GetMapping
    public String addPerson(@RequestParam String name, @RequestParam int age) {
        personList.add(new Person(name, age));
        return "Saved success => " + name + " : " + age;
    }

    // 2. URL 파라미터로 이름 받아서 나이 반환
    @GetMapping("/{name}")
    public Object getAgeByName(@PathVariable String name) {
        for (Person person : personList) {
            if (person.getName().equals(name)) {
                return person.getAge();
            }
        }
        return "Not found " + name;
    }
}
