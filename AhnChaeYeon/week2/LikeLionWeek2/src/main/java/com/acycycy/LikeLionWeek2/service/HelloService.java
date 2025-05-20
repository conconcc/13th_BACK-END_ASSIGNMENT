package com.acycycy.LikeLionWeek2.service;

import com.acycycy.LikeLionWeek2.dto.HelloDto;
import com.acycycy.LikeLionWeek2.entity.HelloEntity;
import com.acycycy.LikeLionWeek2.repository.HelloEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HelloService {
    private final HelloEntityRepository helloEntityRepository;
    public String addUser(HelloDto helloDto) {
        helloEntityRepository.save(helloDto.toEntity());

        return "이름: " + helloDto.getName() + ", 나이: " + helloDto.getAge() + " 추가";
    }

    public HelloDto getUser(String name) {
        Optional<HelloEntity> helloEntity = helloEntityRepository.findByName(name);
        if (helloEntity.isPresent()) {
            return HelloDto.from(helloEntity.get());
        }
        throw new IllegalArgumentException("이름이 존재하지 않습니다");
    }
}
