package com.example.CRM.utils;

import com.example.CRM.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseUtil {

    public static ResponseEntity<ResponseDTO> successResponse(Object responseDataDTO) {

        return new ResponseEntity<>(new ResponseDTO(0, 200, "Success", responseDataDTO), HttpStatus.OK);
    }

    public static ResponseEntity<ResponseDTO> successResponse(Object responseDataDTO, String message) {

        return new ResponseEntity<>(new ResponseDTO(0, 200, message, responseDataDTO), HttpStatus.OK);

    }


    public static ResponseEntity<ResponseDTO> errorResponse() {

        return new ResponseEntity<>(new ResponseDTO(-1, 404, "Failed", null), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<ResponseDTO> errorResponse(String message) {

        return new ResponseEntity<>(new ResponseDTO(-1, 404, message, null), HttpStatus.NOT_FOUND);

    }


}
