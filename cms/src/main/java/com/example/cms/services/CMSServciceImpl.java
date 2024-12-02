package com.example.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.utils.ListUtils;
import com.example.cms.dto.request.UserRequest;
import com.example.cms.dto.response.ResponseBody;
import com.example.cms.dto.response.ResponseCSM;
import com.example.cms.dto.response.ResponseData;
import com.example.cms.model.CaseManagement;
import com.example.cms.repository.CaseManagementRepoitory;
import com.example.cms.utils.ResponseUtil;

import java.util.Optional;

@Service
public class CMSServciceImpl implements CMSServcice {

    @Autowired
    CaseManagementRepoitory caseManagementRepoitory;

    @Override
    public ResponseEntity<ResponseBody> addACase(Long customerId, UserRequest userRequest) {
        CaseManagement caseManagement = new CaseManagement(userRequest, customerId);
        caseManagementRepoitory.save(caseManagement);
        return ResponseUtil
                .successResponse(new ResponseData(ListUtils.createListFromObjects(
                        new ResponseCSM(caseManagement))));
    }

    @Override
    public ResponseEntity<ResponseBody> editACase(Long customerId, Long caseId, UserRequest userRequest) {
        Optional<CaseManagement> optionalCaseManagement = caseManagementRepoitory.findById(caseId);
        if (optionalCaseManagement.isPresent()) {
            CaseManagement caseManagement = optionalCaseManagement.get();
            caseManagement.setSubject(userRequest.getSubject());
            caseManagement.setDescription(userRequest.getDescription());
            caseManagement.setStatus(userRequest.getStatus());
            caseManagement.setPriority(userRequest.getPriority());
            caseManagement.setAssignedTo(userRequest.getAssignedTo());
            caseManagementRepoitory.save(caseManagement);
            return ResponseUtil.successResponse(new ResponseData(ListUtils.createListFromObjects(new ResponseCSM(caseManagement))));
        }

        return null;
    }

}
