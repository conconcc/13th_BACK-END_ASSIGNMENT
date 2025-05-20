package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, eqalsandhashcode, tostring 자동
@NoArgsConstructor
public class ChatRoomResponseDto {
    private Long id;
    private String roomName;
    public ChatRoomResponseDto(Long id, String roomName ) {
        this.id = id;
        this.roomName = roomName;
    }
}
