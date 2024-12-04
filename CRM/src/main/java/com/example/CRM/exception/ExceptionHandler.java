package com.example.CRM.exception;

import com.example.CRM.dto.response.ResponseDTO;
import com.example.CRM.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ResponseDTO> handleNotFoundException(NotFoundException exception) {
        return ResponseUtil.errorResponse(exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {EmptyException.class})
    public ResponseEntity<ResponseDTO> handleEmptyException(EmptyException exception) {
        return ResponseUtil.errorResponse(exception.getMessage());
    }
}
