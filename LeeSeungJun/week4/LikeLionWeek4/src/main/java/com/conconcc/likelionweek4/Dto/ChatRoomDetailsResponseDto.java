package com.conconcc.likelionweek4.Dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, eqalsandhashcode, tostring 자동
@NoArgsConstructor
public class ChatRoomDetailsResponseDto {
    private Long id;
    private String roomName;
    private List<ChatMessageResponseDto> messages;

    public ChatRoomDetailsResponseDto(Long id, String roomName, List<ChatMessageResponseDto> messages) {
        this.id = id;
        this.roomName = roomName;
        this.messages = messages;
    }
}
