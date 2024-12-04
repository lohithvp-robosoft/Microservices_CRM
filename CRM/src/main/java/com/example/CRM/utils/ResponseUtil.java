package com.example.CRM.utils;

import com.example.CRM.dto.response.CustomerAndCaseMapper;
import com.example.CRM.dto.response.ResponseDTO;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ResponseUtil {

    private static WebClient.Builder webClientBuilder;

    @Autowired
    private ResponseUtil(WebClient.Builder webClientBuilder) {
        ResponseUtil.webClientBuilder = webClientBuilder;
    }


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

    public static ResponseEntity<ResponseDTO> errorResponse(String message, int httpCode) {

        return new ResponseEntity<>(new ResponseDTO(-1, httpCode, message, null), HttpStatusCode.valueOf(httpCode));

    }

//


//    public static <T> List<T> extractDataFromResponse(String keyName, String responseJson, Class<T> clazz) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            // Deserialize the response into a ResponseDTO object
//            ResponseDTO responseDTO = objectMapper.readValue(responseJson, ResponseDTO.class);
//
//            // Extract the data object (cases in this case)
//            Map<String, Object> dataMap = (Map<String, Object>) responseDTO.getData();
//
//            // Get the list corresponding to the key name
//            List<T> caseList = (List<T>) dataMap.get(keyName);
//
//            // Directly return the list of objects
//            return caseList;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static <T> List<T> extractDataFromResponse(String keyName, String responseJson, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            ResponseDTO responseDTO = objectMapper.readValue(responseJson, ResponseDTO.class);

            Map<String, List<T>> dataMap = (Map<String, List<T>>) responseDTO.getData();

            return dataMap.get(keyName);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
