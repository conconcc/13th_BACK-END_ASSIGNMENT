package com.jhkim0609.likelionweek1.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;

@RestController
public class HelloController {
    ArrayList<Pair<String, Integer>> myList = new ArrayList<Pair<String, Integer>>();
    @GetMapping("/hello")
    public String hello(@RequestParam String name, @RequestParam Integer age) {
        Pair<String, Integer> p = new Pair<String, Integer>(name, age);
        this.myList.add(p);
        return "name 은 " + name + "이고, 나이는 " + age;
    }
    @GetMapping("/hello/{name1}")
    public String print_age(@PathVariable("name1") String name1){
        for (Pair<String, Integer> p : myList)
            if(name1.equals(p.getFirst()))
                return p.getSecond().toString();
        return "저장되지 않은 사용자";
    }
}
