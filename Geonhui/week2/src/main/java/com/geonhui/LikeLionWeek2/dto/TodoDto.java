package com.geonhui.LikeLionWeek2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {

    @NotNull(message = "투두 제목은 필수입니다.")
    private String title;

    @NotNull(message = "완료 여부는 필수입니다.")
    private boolean isComplete;
}
