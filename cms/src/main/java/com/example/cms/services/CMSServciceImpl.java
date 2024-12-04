package com.example.cms.services;

import com.example.cms.enums.Status;
import com.example.cms.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.cms.utils.ListUtils;
import com.example.cms.dto.request.UserRequest;
import com.example.cms.dto.response.ResponseBody;
import com.example.cms.dto.response.ResponseCSM;
import com.example.cms.dto.response.ResponseData;
import com.example.cms.model.CaseManagement;
import com.example.cms.repository.CaseManagementRepoitory;
import com.example.cms.utils.ResponseUtil;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CMSServciceImpl implements CMSServcice {
    @Value("${error.case.notFound}")
    private String caseNotFoundMessage;

    @Autowired
    CaseManagementRepoitory caseManagementRepository;

    @Override
    public ResponseEntity<ResponseBody> addACase(Long customerId, UserRequest userRequest) {
        CaseManagement caseManagement = new CaseManagement(userRequest, customerId);
        caseManagementRepository.save(caseManagement);
        return ResponseUtil
                .successResponse(new ResponseData(ListUtils.createListFromObjects(
                        new ResponseCSM(caseManagement))));
    }

    @Override
    public ResponseEntity<ResponseBody> editACase(Long customerId, Long caseId, UserRequest userRequest) throws NotFoundException {
        Optional<CaseManagement> optionalCaseManagement = caseManagementRepository.findById(caseId);
        if (optionalCaseManagement.isPresent() && Objects.equals(optionalCaseManagement.get().getCustomerId(), customerId)) {
            CaseManagement caseManagement = optionalCaseManagement.get();
            caseManagement.setSubject(userRequest.getSubject());
            caseManagement.setDescription(userRequest.getDescription());
            caseManagement.setStatus(userRequest.getStatus());
            caseManagement.setPriority(userRequest.getPriority());
            caseManagement.setAssignedTo(userRequest.getAssignedTo());
            caseManagementRepository.save(caseManagement);
            return ResponseUtil.successResponse(new ResponseData(ListUtils.createListFromObjects(new ResponseCSM(caseManagement))));
        }
        throw new NotFoundException(String.format(caseNotFoundMessage, caseId));
    }

    @Override
    public ResponseEntity<ResponseBody> getACaseById(Long customerId, Long caseId) throws NotFoundException {
        Optional<CaseManagement> optionalCaseManagement = caseManagementRepository.findById(caseId);

        if (optionalCaseManagement.isPresent() && Objects.equals(optionalCaseManagement.get().getCustomerId(), customerId)) {
            return ResponseUtil.successResponse(new ResponseData(ListUtils.createListFromObjects(new ResponseCSM(optionalCaseManagement.get()))));
        }
        throw new NotFoundException(String.format(caseNotFoundMessage, caseId));
    }

    @Override
    public ResponseEntity<ResponseBody> deleteACase(Long customerId, Long caseId) throws NotFoundException {
        Optional<CaseManagement> optionalCaseManagement = caseManagementRepository.findById(caseId);

        if (optionalCaseManagement.isPresent() && Objects.equals(optionalCaseManagement.get().getCustomerId(), customerId)) {
            caseManagementRepository.delete(optionalCaseManagement.get());
            return ResponseUtil.successResponse(new ResponseData(ListUtils.createListFromObjects(new ResponseCSM(optionalCaseManagement.get()))));
        }
        throw new NotFoundException(String.format(caseNotFoundMessage, caseId));
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void updateCaseStatus() {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        List<CaseManagement> cases = caseManagementRepository.findCasesToUpdate(oneHourAgo);

        for (CaseManagement caseManagement : cases) {
            caseManagement.setStatus(Status.IN_PROGRESS);
        }

        caseManagementRepository.saveAll(cases);
    }
}
