package com.example.CRM.dto.response;

import com.example.CRM.dto.enums.Priority;
import com.example.CRM.dto.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CaseManagementResponse {
    private Long id;

    private String subject;

    private String description;

    private Status status;

    private Priority priority;

    private Long customerId;

    private String assignedTo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}
