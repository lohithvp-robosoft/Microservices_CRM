package com.example.CRM.exception;

import com.example.CRM.dto.response.ResponseDTO;
import com.example.CRM.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
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

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseDTO> handleUnhandledException(Exception exception) {
        Throwable cause = exception.getCause();

        if (cause instanceof NotFoundException) {
            NotFoundException notFoundException = (NotFoundException) cause;
            return ResponseUtil.errorResponse(notFoundException.getMessage(), HttpStatus.NOT_FOUND.value());
        }

        return ResponseUtil.errorResponse(exception.getMessage(), 500);
    }
}
