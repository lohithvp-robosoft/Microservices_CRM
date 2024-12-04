package com.example.cms.model;

import com.example.cms.dto.request.UserRequest;
import com.example.cms.enums.Priority;
import com.example.cms.enums.Status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "case_management")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CaseManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_seq")
    @SequenceGenerator(name = "case_seq", sequenceName = "case_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    private String subject;

    private String description;

    private Status status;

    private Priority priority;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt; // Set initial update time to created time
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getFormattedId() {
        return String.format("%08d", id);
    }

    public CaseManagement(UserRequest userRequest, Long customerId) {
        this.subject = userRequest.getSubject();
        this.description = userRequest.getDescription();
        this.status = userRequest.getStatus();
        this.priority = userRequest.getPriority();
        this.assignedTo = userRequest.getAssignedTo();
        this.customerId = customerId;
    }

}
