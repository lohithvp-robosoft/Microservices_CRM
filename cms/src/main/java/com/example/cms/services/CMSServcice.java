package com.example.cms.services;

import com.example.cms.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.request.UserRequest;
import com.example.cms.dto.response.ResponseBody;

import java.io.FileNotFoundException;

@Service
public interface CMSServcice {

    ResponseEntity<ResponseBody> addACase(Long customerId, UserRequest userRequest);

    ResponseEntity<ResponseBody> editACase(Long customerId, Long caseId, UserRequest userRequest) throws NotFoundException;

    ResponseEntity<ResponseBody> getACaseById(Long customerId, Long caseId) throws NotFoundException;

    ResponseEntity<ResponseBody> deleteACase(Long customerId, Long caseId) throws NotFoundException;
}
