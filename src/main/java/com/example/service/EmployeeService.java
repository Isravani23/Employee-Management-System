package com.example.service;

import com.example.entity.EmployeeDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeService {
    
    List<EmployeeDetails> findAll();
    
    EmployeeDetails findById(Long theId);

    EmployeeDetails save(EmployeeDetails employeeDetails);

    EmployeeDetails deleteById(Long theId);

    Page<EmployeeDetails> getEmployees(int page, int size);
}
