package com.example.CRM.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CustomerRequestDTO {
    private String firstName;

    private String lastName;

    private Long phone;

    private String email;

    private String address;
}
