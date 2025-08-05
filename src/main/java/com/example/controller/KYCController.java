package com.example.controller;

import com.example.dto.KYCRequest;
import com.example.entity.KYCdetails;
import com.example.service.KYCService;
import com.example.utility.KYCStatusConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kyc")
public class KYCController {

    @Autowired
    private KYCService kycService;

    @PostMapping("/submit")
    public ResponseEntity<KYCdetails> submitKyc(@RequestBody KYCRequest request) {
        return ResponseEntity.ok(kycService.submitKyc(request));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<KYCdetails> getKyc(@PathVariable Long employeeId) {
        return ResponseEntity.ok(kycService.getKycByEmployee(employeeId));
    }

    @PutMapping("/{kycId}/status")
    public ResponseEntity<KYCdetails> updateStatus(@PathVariable Long kycId, @RequestParam KYCStatusConstants status) {
        return ResponseEntity.ok(kycService.updateKycStatus(kycId, status));
    }
}
