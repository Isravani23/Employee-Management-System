package com.example.service;

import com.example.entity.EmployeeDetails;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDetails> findAll();
    EmployeeDetails findById(Long theId);

    EmployeeDetails save(EmployeeDetails employeeDetails);

    EmployeeDetails deleteById(Long theId);
}
