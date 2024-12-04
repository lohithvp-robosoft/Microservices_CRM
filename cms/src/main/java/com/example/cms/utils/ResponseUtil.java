package com.example.cms.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.example.cms.dto.response.ResponseBody;

public class ResponseUtil {

	public static ResponseEntity<ResponseBody> successResponse(Object responseData) {
		return new ResponseEntity<>(new ResponseBody(0, 200, "Success", responseData), HttpStatus.OK);
	}

	public static ResponseEntity<ResponseBody> successResponse(Object responseData, String message) {
		return new ResponseEntity<>(new ResponseBody(0, 200, message, responseData), HttpStatus.OK);
	}

	public static ResponseEntity<ResponseBody> errorResponse() {

		return new ResponseEntity<>(new ResponseBody(-1, 404, "Failed", null), HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<ResponseBody> errorResponse(String message) {

		return new ResponseEntity<>(new ResponseBody(-1, 404, message, null), HttpStatus.NOT_FOUND);

	}
	public static ResponseEntity<ResponseBody> errorResponse(String message, int httpStatus) {

		return new ResponseEntity<>(new ResponseBody(-1, httpStatus, message, null), HttpStatusCode.valueOf(httpStatus));

	}

	private ResponseUtil() {
	}
}
