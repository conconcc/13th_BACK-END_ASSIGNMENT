package com.conconcc.LikeLionWeek2.entity;


import com.conconcc.LikeLionWeek2.dto.TodoDto;
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
    @Column(nullable = false, unique = true)
    private String title = "기본 제목";
    @Column(name = "todo_state", nullable = false)
    private boolean state;

    public void update(TodoDto dto){
        if(dto.getTitle()!=null){
            if(dto.getTitle().trim().isEmpty()){
                throw new IllegalArgumentException("제목이 비어있을 수 없습니다.");
            }
            this.title=dto.getTitle();
        }
        if(dto.isState()!=state){
            this.state=dto.isState();
        }

    }

    public void togglestate() {
        this.state=!state;
    }
}
