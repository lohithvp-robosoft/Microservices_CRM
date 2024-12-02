package com.example.cms.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.cms.dto.response.ResponseBody;

public class ResponseUtil {

	public static ResponseEntity<ResponseBody> successResponse(Object responseData) {
		return new ResponseEntity<>(new ResponseBody(0, 200, "Success", responseData), HttpStatus.OK);
	}

	public static ResponseEntity<ResponseBody> successResponse(Object responseData, String message) {
		return new ResponseEntity<>(new ResponseBody(0, 200, message, responseData), HttpStatus.OK);
	}

	private ResponseUtil() {
	}
}
