package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor

public class ChatMessageResponseDto {
    private Long id;
    private Long senderId;
    private String senderUsername;
    private Long chatRoomId;
    private String content;
    private LocalDateTime timestamp;

    public ChatMessageResponseDto(Long id, Long senderId, String senderUsername, Long chatRoomId, String content, LocalDateTime timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.chatRoomId = chatRoomId;
        this.content = content;
        this.timestamp = timestamp;
    }
}
