package kkt9253.likelionweek2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private boolean isDone;

    @Builder
    public Todo(String content) {

        this.content = content;
        // 할 일을 추가할 때 이미 완료된 일을 추가하는 경우는 없다고 판단하여 false로 기본설정 함
        this.isDone = false;
    }

    public void changeIsDone() {

        this.isDone = !isDone;
    }
}
