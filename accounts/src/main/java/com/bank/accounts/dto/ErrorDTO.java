package com.bank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private String api;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTimeStamp;
}
