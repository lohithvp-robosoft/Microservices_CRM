package com.example.CRM.repository;

import com.example.CRM.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
