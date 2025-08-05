package com.example.repository;


import com.example.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Long> {
    Optional<EmployeeDetails> findByEmail(String email);
}
