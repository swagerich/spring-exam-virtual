package com.erich.exam.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailCustomDto {

    private List<String> toEmails;

    private String message;
}
