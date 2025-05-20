package com.conconcc.likelionweek4.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, eqalsandhashcode, tostring 자동
@NoArgsConstructor
public class UserRegisterRequestDto {
    private Long id;
    private String username;
    private String password;
    private String confirmPassword;
}
