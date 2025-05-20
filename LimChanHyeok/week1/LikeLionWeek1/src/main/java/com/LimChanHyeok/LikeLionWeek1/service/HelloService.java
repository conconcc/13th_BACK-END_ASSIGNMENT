package com.LimChanHyeok.LikeLionWeek1.service;

import com.LimChanHyeok.LikeLionWeek1.controller.HelloController;
import com.LimChanHyeok.LikeLionWeek1.dto.HelloDto;
import com.LimChanHyeok.LikeLionWeek1.entity.HelloEntity;
import com.LimChanHyeok.LikeLionWeek1.repository.HelloEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final HelloEntityRepository helloEntityRepository;
    private final List<HelloDto> userList = new ArrayList<>();

    public String addUser(String name,int age) {
        HelloController.User user = new HelloController.User(name, age);
        userList.add(new HelloDto(name, age));
        return "사용자 추가 완료: " + name + ", " + age;
    }

    public HelloEntity getUser(String name) {
        Optional<HelloEntity> helloEntity = helloEntityRepository.findByName(name);
        if(helloEntity.isPresent()) {
            return helloEntity.get();
        }
        throw new IllegalArgumentException("이름이 존재하지 않습니다");
    }

    public List<HelloDto> getUserList() {
        return userList;
    }


    public HelloDto addUser(HelloDto helloDto) {
        return helloDto;
    }
}
