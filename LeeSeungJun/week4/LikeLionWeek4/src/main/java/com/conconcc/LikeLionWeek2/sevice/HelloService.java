package com.conconcc.LikeLionWeek2.sevice;

import com.conconcc.LikeLionWeek2.dto.HelloDto;
import com.conconcc.LikeLionWeek2.entity.HelloEntity;
import com.conconcc.LikeLionWeek2.repository.HelloEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final HelloEntityRepository helloEntityRepository;

    public String addUser(HelloDto helloDto) {
        helloEntityRepository.save(helloDto.toEntity());
        return "이름: " + helloDto.getName() + " 나이: " + helloDto.getAge() + " 추가";
    }
    public HelloDto getUser(String name) {
        Optional<HelloEntity> helloEntity = helloEntityRepository.findByName(name);
        if (helloEntity.isPresent()) {
            return HelloDto.fromEntity(helloEntity.get());
        }
        throw new IllegalArgumentException("이름이 존재하지 않습니다");
    }
    public List<String> findAllAsStringList() {
        return helloEntityRepository.findAll()
                .stream()
                .map(HelloDto::fromEntity)
                .map(dto -> "이름: " + dto.getName() + ", 나이: " + dto.getAge())
                .collect(Collectors.toList());
    }

    public void deleteUser(String name) {
        Optional<HelloEntity> helloEntity = helloEntityRepository.findByName(name);
        if (!helloEntity.isPresent()) {
            throw new IllegalArgumentException("이름이 존재하지 않습니다.");
        }
        helloEntityRepository.delete(helloEntity.get());
    }

    public void deleteUserById(Long id) {
        Optional<HelloEntity> helloEntity = helloEntityRepository.findById(id);
        if (!helloEntity.isPresent()) {
            throw new IllegalArgumentException("해당 Id는 존재하지 않습니다.");
        }
        helloEntityRepository.deleteById(id);
    }


//    public List<String> all(){
//        List<String> list= new ArrayList<>();
//        list.addAll(map.keySet());
//        for (String key:map.keySet()){
//            list.add(key);
//        }
//        return list;
//    }


}
