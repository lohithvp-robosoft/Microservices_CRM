package com.example.CRM.model;

import com.example.CRM.dto.request.NotesRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Table
@Entity
@NoArgsConstructor
public class CustomerNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @Column(name = "text")
    private String text;

    public CustomerNotes(NotesRequestDTO notesRequestDTO, Customer customer) {
        this.text = notesRequestDTO.getText();
        this.customer = customer;
    }

}
