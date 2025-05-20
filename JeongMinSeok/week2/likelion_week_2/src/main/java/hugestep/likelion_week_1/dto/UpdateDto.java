package hugestep.likelion_week_1.dto;

import hugestep.likelion_week_1.entity.HelloEntity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDto {
    private Long id; // 해야하는 일 번호
    private String toDo;   // 해야하는 일
    private boolean completeState; // 완료 유무

    public static UpdateDto from(HelloEntity helloEntity) { // Entity를 dto로 변환
        return UpdateDto.builder()
                .id(helloEntity.getId())
                .toDo(helloEntity.getToDo())
                .completeState(helloEntity.isCompleteState())
                .build();
    }

    public HelloEntity toEntity() { // dto를 Entity로 변환
        return HelloEntity.builder()
                .id(id)
                .toDo(toDo)
                .completeState((completeState))
                .build();
    }
}
