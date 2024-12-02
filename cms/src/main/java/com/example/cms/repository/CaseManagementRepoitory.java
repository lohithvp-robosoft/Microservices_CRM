package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.model.CaseManagement;

public interface CaseManagementRepoitory extends JpaRepository<CaseManagement, Long> {

}
