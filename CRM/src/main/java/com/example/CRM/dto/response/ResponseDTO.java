package com.example.CRM.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private int status;
    private int code;
    private String message;
    private Object data;

}
