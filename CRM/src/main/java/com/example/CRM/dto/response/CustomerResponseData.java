package com.example.CRM.dto.response;

import com.example.CRM.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class CustomerResponseData {
    ArrayList<Customer> customers;
}
