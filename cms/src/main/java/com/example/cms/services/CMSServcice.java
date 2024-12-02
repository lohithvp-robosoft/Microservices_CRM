package com.example.cms.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.request.UserRequest;
import com.example.cms.dto.response.ResponseBody;

@Service
public interface CMSServcice {

    ResponseEntity<ResponseBody> addACase(Long customerId, UserRequest userRequest);

    ResponseEntity<ResponseBody> editACase(Long customerId, Long caseId, UserRequest userRequest);
}
