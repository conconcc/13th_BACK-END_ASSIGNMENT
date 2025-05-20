package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnreadMessageCountResponseDto {
    private Long chatRoomId;
    private Long userId;
    private Long unreadCount;

    public UnreadMessageCountResponseDto(Long chatRoomId, Long userId, Long unreadCount) {
        this.chatRoomId=chatRoomId;
        this.userId=userId;
        this.unreadCount=unreadCount;
    }
}
