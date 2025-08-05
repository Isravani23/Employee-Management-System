package com.example.service;

import com.example.repository.RoleRepository;
import com.example.entity.EmployeeRole;
import com.example.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    @Override
    public EmployeeRole save(EmployeeRole role) {

        return roleRepository.save(role);
    }

    @Override
    public EmployeeRole deleteById(int theId) {

        return null;
    }

    @Override
    public List<EmployeeRole> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public EmployeeRole findById(int theId) {
        Optional<EmployeeRole> result = roleRepository.findById(theId);
        EmployeeRole employeeRole = null;
        if(result.isPresent()){
            employeeRole = result.get();
        }else{
            throw new EmployeeNotFoundException("Did not foind role id"+theId);
        }
        return employeeRole;
    }
}
