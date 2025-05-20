package hugestep.likelion_week_1.dto;

import hugestep.likelion_week_1.entity.HelloEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDto {
    private String toDo;   // 해야하는 일
    private boolean completeState; // 완료 유무

    public static CreateDto from(HelloEntity helloEntity) { // Entity를 dto로 변환
        return CreateDto.builder()
                .toDo(helloEntity.getToDo())
                .completeState(helloEntity.isCompleteState())
                .build();
    }

    public HelloEntity toEntity() { // dto를 Entity로 변환
        return HelloEntity.builder()
                .toDo(toDo)
                .completeState((completeState))
                .build();
    }
}