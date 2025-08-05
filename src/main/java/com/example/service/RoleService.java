package com.example.service;

import com.example.entity.EmployeeRole;

import java.util.List;

public interface RoleService {

    List<EmployeeRole> findAll();
    EmployeeRole findById(int theId);

    EmployeeRole save(EmployeeRole employeeRole);

    EmployeeRole deleteById(int theId);
}
