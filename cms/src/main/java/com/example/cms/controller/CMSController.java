package com.example.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.cms.dto.request.UserRequest;
import com.example.cms.dto.response.ResponseBody;
import com.example.cms.services.CMSServcice;

@Controller
@RequestMapping("/api")
public class CMSController {

    @Autowired
    CMSServcice cmsServcice;

    @PostMapping("/v1/{customerId}/case")
    public ResponseEntity<ResponseBody> addACase(@RequestBody UserRequest userRequest, @PathVariable Long customerId) {
        return cmsServcice.addACase(customerId, userRequest);
    }

    @PutMapping("/v1/{customerId}/case/{caseId}")
    public ResponseEntity<ResponseBody> updateACase(@RequestBody UserRequest userRequest, @PathVariable Long customerId, @PathVariable Long caseId) {
        return cmsServcice.editACase(customerId, caseId, userRequest);
    }
}
