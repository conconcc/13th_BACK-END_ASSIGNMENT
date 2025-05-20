package com.example.week2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false, nullable = false)   // 같은 날짜 중복허용, null은 허용 안함
    private String whatToDo;

    @Column(name = "Done")
    private Boolean done = false;  // 했는지 확인하는 변수, 기본값은 false다

    public void setWhatToDo(String whatToDO) {
        this.whatToDo = whatToDO;
    }

    public void setDone(Boolean newStatus) { this.done = newStatus;}
}
