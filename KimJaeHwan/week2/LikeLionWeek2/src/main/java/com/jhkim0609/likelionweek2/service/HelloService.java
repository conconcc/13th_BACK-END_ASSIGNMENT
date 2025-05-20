package com.jhkim0609.likelionweek2.service;

import com.jhkim0609.likelionweek2.dto.HelloDto;
import com.jhkim0609.likelionweek2.entity.HelloEntity;
import com.jhkim0609.likelionweek2.repository.HelloEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final HelloEntityRepository helloEntityRepository;
    public String addUser(HelloDto helloDto) {
        helloEntityRepository.save(helloDto.toEntity());
        return helloDto.getName()+":"+helloDto.getAge();
    }
    public HelloDto getUser(String name) {
        Optional<HelloEntity> helloEntity = helloEntityRepository.findByName(name);
        if(helloEntity.isPresent()) {
            return HelloDto.fromEntity(helloEntity.get());
        }
        throw new IllegalArgumentException("이름이 리스트에 없음");
    }
}