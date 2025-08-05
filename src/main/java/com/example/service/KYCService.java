package com.example.service;

import com.example.dto.KYCRequest;
import com.example.entity.EmployeeDetails;
import com.example.entity.KYCdetails;
import com.example.repository.EmployeeRepository;
import com.example.repository.KYCRepository;
import com.example.utility.KYCStatusConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class KYCService {
    @Autowired
    private KYCRepository kycRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public KYCdetails submitKyc(KYCRequest request){
        EmployeeDetails emp = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(()->new RuntimeException("Employee not found"));
        kycRepository.findByEmployeeDetails_Id(emp.getId())
                .ifPresent(k-> {throw new RuntimeException("Record already present");});

        KYCdetails kyc = new KYCdetails();
        kyc.setDocumentType(request.getDocumentType());
        kyc.setDocumentNum(request.getDocumentNumber());
        kyc.setSubmissionDate(LocalDate.now());
        kyc.setStatus(KYCStatusConstants.PENDING);
        kyc.setEmployeeDetails(emp);

        return kycRepository.save(kyc);
    }


    public KYCdetails getKycByEmployee(Long employeeId) {
        return kycRepository.findByEmployeeDetails_Id(employeeId)
                .orElseThrow(() -> new RuntimeException("KYC not found"));
    }
    public KYCdetails updateKycStatus(Long kycId, KYCStatusConstants status) {
        KYCdetails kyc = kycRepository.findById(kycId)
                .orElseThrow(() -> new RuntimeException("KYC not found"));
        kyc.setStatus(String.valueOf(status));
        return kycRepository.save(kyc);
    }

}
