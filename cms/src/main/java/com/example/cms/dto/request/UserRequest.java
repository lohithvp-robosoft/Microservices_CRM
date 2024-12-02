package com.example.cms.dto.request;

import com.example.cms.enums.Priority;
import com.example.cms.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	private String subject;

	private String description;

	private Status status;

	private Priority priority;

	private String assignedTo;

}
