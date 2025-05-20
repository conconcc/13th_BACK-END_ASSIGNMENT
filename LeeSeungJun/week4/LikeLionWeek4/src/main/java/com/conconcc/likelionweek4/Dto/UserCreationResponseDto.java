package com.conconcc.likelionweek4.Dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data //getter, setter, eqalsandhashcode, tostring 자동
@NoArgsConstructor
public class UserCreationResponseDto {
    private String username;
    private Long id;
    private Timestamp created_at;

    public UserCreationResponseDto(String username, Long id, Timestamp created_at){
        this.username = username;
        this.id = id;
        this.created_at = created_at;
    }

}
