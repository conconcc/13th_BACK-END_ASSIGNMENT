package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessageRequestDto {
    private Long senderId;
    private Long chatRoomId;
    private String content;
}
