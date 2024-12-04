package com.example.CRM.repository;

import com.example.CRM.model.CustomerNotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerNotesRepository extends JpaRepository<CustomerNotes, Long> {
}
