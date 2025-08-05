package com.example.mapper;

import com.example.dto.EmployeeResponse;
import com.example.entity.EmployeeDetails;
import com.example.entity.EmployeeRole;
import com.example.entity.KYCdetails;

public class EmployeeMapper {

    public static EmployeeResponse toDto(EmployeeDetails employee) {
        EmployeeResponse dto = new EmployeeResponse();

        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setEmploymentType(employee.getEmploymentType());
        dto.setWorkLocation(employee.getWorkLocation());
        dto.setJoiningDate(employee.getJoiningDate());
        dto.setCreatedAt(employee.getCreatedAt());
        dto.setUpdatedAt(employee.getUpdatedAt());

        // Set Role
        if (employee.getRole() != null) {
            EmployeeResponse.RoleDTO roleDTO = new EmployeeResponse.RoleDTO();
            roleDTO.setId(employee.getRole().getId());
            roleDTO.setRoleName(employee.getRole().getRole());
            roleDTO.setLevel(employee.getRole().getLevel());
            roleDTO.setDepartment(employee.getRole().getDepartment());
            dto.setRole(roleDTO);
        }

        // Set KYC
        if (employee.getKyc() != null) {
            EmployeeResponse.KycDTO kycDTO = new EmployeeResponse.KycDTO();
            kycDTO.setId(employee.getKyc().getId());
            kycDTO.setDocumentType(employee.getKyc().getDocumentType());
            kycDTO.setDocumentNumber(employee.getKyc().getDocumentNum());
            kycDTO.setAddress(employee.getKyc().getAddress());
            kycDTO.setKycCompleted(employee.getKyc().getStatus());
            dto.setKyc(kycDTO);
        }

        return dto;
    }

}
