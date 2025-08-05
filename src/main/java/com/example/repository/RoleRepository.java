package com.example.repository;

import com.example.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<EmployeeRole ,Integer> {
}
