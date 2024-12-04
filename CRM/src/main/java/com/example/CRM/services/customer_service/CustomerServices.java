package com.example.CRM.services.customer_service;

import com.example.CRM.dto.request.CaseManagementRequest;
import com.example.CRM.dto.request.CustomerRequestDTO;
import com.example.CRM.dto.response.ResponseDTO;
import com.example.CRM.exception.EmptyException;
import com.example.CRM.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerServices {

    ResponseEntity<ResponseDTO> addCustomer(CustomerRequestDTO customerRequestDTO);

    ResponseEntity<ResponseDTO> getAllCustomer() throws EmptyException;

    ResponseEntity<ResponseDTO> getOneCustomerById(Long id) throws NotFoundException;

    ResponseEntity<ResponseDTO> findCustomerByFirstAndLastName(String name) throws NotFoundException;

    ResponseEntity<ResponseDTO> updateCustomerById(Long id, CustomerRequestDTO updatedCustomerDetail) throws NotFoundException;

    ResponseEntity<ResponseDTO> deleteCustomerById(Long id) throws NotFoundException;


    // Case
    ResponseEntity<ResponseDTO> addACase(CaseManagementRequest caseManagementRequest, Long customerId) throws NotFoundException;

    ResponseEntity<ResponseDTO> updateACase(CaseManagementRequest caseManagementRequest, Long customerId, Long caseId) throws NotFoundException;

    ResponseEntity<ResponseDTO> getACase(Long customerId, Long caseId) throws NotFoundException;

    ResponseEntity<ResponseDTO> deleteACase(Long customerId, Long caseId) throws NotFoundException;
}
