package com.erich.exam.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Integer phone;

    private byte[] profile;

}
