package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //getter, setter, eqalsandhashcode, tostring 자동
@NoArgsConstructor
public class ChatRoomDetailsRequestDto {
    private Long id;
    private String roomName;
    private List<ChatMessageResponseDto> messages;


}
