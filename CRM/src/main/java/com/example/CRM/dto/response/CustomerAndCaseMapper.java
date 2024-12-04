package com.example.CRM.dto.response;

import java.util.List;

//import com.example.CRM.client.CaseManagement;
import com.example.CRM.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerAndCaseMapper {
    private Customer customer;
    private List<CaseManagementResponse> cases;
}
