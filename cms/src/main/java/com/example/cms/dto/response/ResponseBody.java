package com.example.cms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseBody {
	private int status;
	private int code;
	private String message;
	private Object data;
}
