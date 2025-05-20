package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor

public class ChatReadStatusResponseDto {
    private Long readStatusId;
    private Long chatId;
    private Long userId;
    private boolean isRead=false;
    private Timestamp readAt;

    public ChatReadStatusResponseDto(Long readStatusId, Long chatId, Long userId, boolean isRead, Timestamp readAt) {
        this.readStatusId = readStatusId;
        this.chatId = chatId;
        this.userId = userId;
        this.isRead = isRead;
        this.readAt = readAt;
    }
}
