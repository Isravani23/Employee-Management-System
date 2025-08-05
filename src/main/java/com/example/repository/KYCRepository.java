package com.example.repository;

import com.example.entity.KYCdetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KYCRepository extends JpaRepository<KYCdetails, Long> {
    Optional<KYCdetails> findByEmployeeDetails_Id(Long id);
}

