package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.model.CaseManagement;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CaseManagementRepoitory extends JpaRepository<CaseManagement, Long> {

//    @Query("SELECT c FROM CaseManagement c WHERE c.status = 'NEW' AND c.createdAt <= :time")
//    List<CaseManagement> findCasesToUpdate(LocalDateTime time);
@Query(value = "SELECT * FROM case_management.case_management WHERE status = 'NEW' AND created_at <= :time", nativeQuery = true)
List<CaseManagement> findCasesToUpdate(LocalDateTime time);

}
