package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatReadStatusRequestDto {
    private Long chatId;
    private Long userId;
}
