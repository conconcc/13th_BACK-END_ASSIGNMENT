package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, eqalsandhashcode, tostring 자동
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;

    public UserResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
