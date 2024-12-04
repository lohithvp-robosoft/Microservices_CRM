package com.example.CRM.dto.request;

import com.example.CRM.dto.enums.Priority;
import com.example.CRM.dto.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaseManagementRequest {

    private String subject;

    private String description;

    private Status status;

    private Priority priority;

    private String assignedTo;

    public CaseManagementRequest(String subject, String description, Status status, Priority priority, String assignedTo) {
        this.subject = subject;
        this.description = description;
        this.priority = priority;
        this.assignedTo = (assignedTo == null || assignedTo.isEmpty()) ? "Unassigned" : assignedTo;
        this.status = (status == null) ? Status.NEW : status;
    }

    CaseManagementRequest() {
        this.assignedTo = "Unassigned";
        this.status = Status.NEW;
    }

}
