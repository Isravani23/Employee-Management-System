package com.example.dto;

public class KYCRequest {
    private Long employeeId;
    private String documentType;
    private String documentNumber;
    private String address;

    public KYCRequest(Long employeeId, String address, String documentNumber, String documentType) {
        this.employeeId = employeeId;
        this.address = address;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}
