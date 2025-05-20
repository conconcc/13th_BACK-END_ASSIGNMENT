package com.conconcc.likelionweek4.Dto;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, eqalsandhashcode, tostring 자동
@NoArgsConstructor
public class ChatRoomCreationResponseDto {
    private Long id;
    private String roomName;
    private Timestamp created_at;

    public ChatRoomCreationResponseDto(Long id, String roomName, Timestamp created_at) {
        this.id = id;
        this.roomName = roomName;
        this.created_at = created_at;
    }
}
