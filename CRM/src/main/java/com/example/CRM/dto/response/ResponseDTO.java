package com.example.CRM.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDTO {
    private int status;
    private int code;
    private String message;
    private Object data;
}
