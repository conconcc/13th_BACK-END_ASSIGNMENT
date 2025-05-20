package hugestep.likelion_week_1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class week1 {

    HashMap<String, Integer> userInfo = new HashMap<String, Integer>();

    @GetMapping("/test")
    public String test(@RequestParam String name, @RequestParam Integer age) {
        userInfo.put(name, age);  // 해시맵에 이름(키), 나이(값) 넣기
        return "이름: "+name + " 나이: "+age;
    }

    @GetMapping("/test/{name}")
    public String test2(@PathVariable String name) {
        if (userInfo.containsKey(name)) { // 이름이 존재하는지 확인
            return name+"의 나이는 "+userInfo.get(name)+"입니다."; // 해시맵에서 이름(키)으로 나이(값) 찾아 내보내기
        }else{
            return "해당하는 이름이 존재하지 않습니다.";
        }
    }
}
