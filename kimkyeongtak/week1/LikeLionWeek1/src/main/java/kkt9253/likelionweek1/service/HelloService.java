package kkt9253.likelionweek1.service;

import kkt9253.likelionweek1.dto.PersonDto;
import kkt9253.likelionweek1.entity.Person;
import kkt9253.likelionweek1.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelloService {

    private final PersonRepository personRepository;

    private final List<PersonDto> personList = new ArrayList<>();

    public String addPerson(String name, Integer age) {
        personList.add(PersonDto.builder()
                .name(name)
                .age(age)
                .build());
        return "Saved success => " + name + " : " + age;
    }

    public String getAgeByName(String name) {

        Optional<PersonDto> personName = personList.stream().filter(personDto -> personDto.getName().equals(name))
                .findFirst();

        return personName.map(personDto -> personDto.getName() + "의 나이는 " + personDto.getAge() + "입니다.")
                .orElseGet(() -> "Not found " + name);
    }


    public List<PersonDto> getAllUser() {

        List<Person> personList = personRepository.findAll();





//        StringBuilder sb = new StringBuilder();
//
//        personList.stream().map(PersonDto::getName).forEach(name -> sb.append(name).append(", "));
//
//        return sb.substring(0, sb.length() - 2);
    }
}
