package com.example.cms.dto.response;

import com.example.cms.enums.Priority;
import com.example.cms.enums.Status;
import com.example.cms.model.CaseManagement;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class ResponseCSM {

	private Long id;

	private String subject;

	private String description;

	private Status status;

	private Priority priority;

	private Long customerId;

	private String assignedTo;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public ResponseCSM(CaseManagement caseManagement) {
		this.id = caseManagement.getId();
		this.subject = caseManagement.getSubject();
		this.description = caseManagement.getDescription();
		this.status = caseManagement.getStatus();
		this.priority = caseManagement.getPriority();
		this.customerId = caseManagement.getCustomerId();
		this.assignedTo = caseManagement.getAssignedTo();
		this.createdAt = caseManagement.getCreatedAt();
		this.updatedAt = caseManagement.getUpdatedAt();
	}
}
