package hugestep.likelion_week_1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // Entity 클래스로 생성
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HelloEntity {
    @Id // 붙이면 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 DB에 위임
    private Long id;

    @Column(unique = true, nullable = false)
    private String toDo;

    @Column(nullable = false)
    private boolean completeState;

}
