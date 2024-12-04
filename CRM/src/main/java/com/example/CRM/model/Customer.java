package com.example.CRM.model;

import com.example.CRM.dto.request.CustomerRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Table
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerNotes> notes = new ArrayList<>();

    public Customer(CustomerRequestDTO customerRequestDTO) {
        this.firstName = customerRequestDTO.getFirstName();
        this.lastName = customerRequestDTO.getLastName();
        this.phone = customerRequestDTO.getPhone();
        this.email = customerRequestDTO.getEmail();
        this.address = customerRequestDTO.getAddress();
    }
}
