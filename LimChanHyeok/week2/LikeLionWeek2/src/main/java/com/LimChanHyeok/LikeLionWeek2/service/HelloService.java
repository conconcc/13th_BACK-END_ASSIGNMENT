package com.LimChanHyeok.LikeLionWeek2.service;

import com.LimChanHyeok.LikeLionWeek2.dto.HelloDto;
import com.LimChanHyeok.LikeLionWeek2.entity.HelloEntity;
import com.LimChanHyeok.LikeLionWeek2.repository.HelloEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final HelloEntityRepository helloEntityRepository;

    public String addUser(HelloDto helloDto) {
        helloEntityRepository.save(helloDto.toEntity());
        return "이름: " + helloDto.getName() + "나이: " + helloDto.getAge() + "추가";
    }

    public HelloDto getUser(String name) {
        Optional<HelloEntity> helloEntity = helloEntityRepository.findByName(name);
        if(helloEntity.isPresent()) {
            return HelloDto.fromEntity(helloEntity.get());
        }
        throw new IllegalArgumentException("이름이 존재하지 않습니다.");
    }

    public List<HelloDto> findAll() {
        List<HelloEntity> entities = helloEntityRepository.findAll();

        List<HelloDto> helloDtoList = new ArrayList<>();
        for (HelloEntity entity : entities) {
            helloDtoList.add(HelloDto.fromEntity(entity));
        }
        return helloDtoList;
    }

    public String deleteAllUsers() {
        helloEntityRepository.deleteAll();
        return "모든 사용자 데이터를 삭제했습니다.";
    }

    public String deleteById(Long id) {
        boolean exists = helloEntityRepository.existsById(id);
        if(!exists) {
            throw new IllegalArgumentException("해당 Id를 가진 사람이 없습니다.");
        }

        helloEntityRepository.deleteById(id);
        return "Id가 " + id + "인 사용자를 삭제했습니다.";
    }

}
