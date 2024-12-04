package com.example.CRM.controller;

import com.example.CRM.dto.request.CustomerRequestDTO;
import com.example.CRM.dto.response.ResponseDTO;
import com.example.CRM.exception.EmptyException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.services.customer_service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerServices customerServices;

    @PostMapping("/v1/customer")
    public ResponseEntity<ResponseDTO> addCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerServices.addCustomer(customerRequestDTO);
    }

    @GetMapping("/v1/customer")
    public ResponseEntity<ResponseDTO> getOneCustomerById(@RequestHeader("id") Long id) throws NotFoundException {
        return customerServices.getOneCustomerById(id);
    }

    @PutMapping("/v1/customer")
    public ResponseEntity<ResponseDTO> updateCostumer(@RequestHeader("id") Long id, @RequestBody CustomerRequestDTO updatedCustomer) throws NotFoundException {
        return customerServices.updateCustomerById(id, updatedCustomer);
    }

    @DeleteMapping("/v1/customer")
    public ResponseEntity<ResponseDTO> deleteCustomerById(@RequestHeader("id") Long id) throws NotFoundException {
        return customerServices.deleteCustomerById(id);
    }

    @GetMapping("/v1/AllCustomers")
    public ResponseEntity<ResponseDTO> getAllCustomers() throws EmptyException {
        return customerServices.getAllCustomer();
    }

    @GetMapping("/v1/FindCustomer")
    public ResponseEntity<ResponseDTO> findOneCustomerByName(@RequestParam("name") String name) throws NotFoundException {
        return customerServices.findCustomerByFirstAndLastName(name);
    }
}
