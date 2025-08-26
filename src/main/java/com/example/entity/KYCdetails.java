package com.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="kycdetails")
public class KYCdetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentType;
    private String documentNum;
    private String address;
    private String status;
    private LocalDate submissionDate;
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private EmployeeDetails employeeDetails;

    public KYCdetails(Long employeeId) {

    }

    public KYCdetails(EmployeeDetails employeeDetails, String status, String address, String documentNum, String documentType, Long id, LocalDate submissionDate, Long employeeId) {
        this.employeeDetails = employeeDetails;
        this.status = status;
        this.address = address;
        this.documentNum = documentNum;
        this.documentType = documentType;
        this.id = id;

    }

    public KYCdetails() {

    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Long getId() {
        return id;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails emp) {
        this.employeeDetails = emp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(String documentNum) {
        this.documentNum = documentNum;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setEmployee(EmployeeDetails employee) {
        this.employeeDetails=employee;
    }
    public EmployeeDetails getEmployee() {
        return employeeDetails;
    }
    
}
