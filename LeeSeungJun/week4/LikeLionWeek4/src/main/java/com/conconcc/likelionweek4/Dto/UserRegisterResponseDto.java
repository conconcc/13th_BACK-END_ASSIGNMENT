package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, eqalsandhashcode, tostring 자동
@NoArgsConstructor
public class UserRegisterResponseDto {
    private Long id;
    private String username;

    public UserRegisterResponseDto(Long id,String username) {
        this.username = username;
        this.id = id;
    }
}
