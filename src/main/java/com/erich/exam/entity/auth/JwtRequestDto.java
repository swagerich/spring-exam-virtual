package com.erich.exam.entity.auth;

import lombok.Data;

@Data
public class JwtRequestDto {

    private String username;

    private String password;
}
