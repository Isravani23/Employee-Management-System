package com.example.service;

import com.example.repository.EmployeeRepository;
import com.example.entity.EmployeeDetails;
import com.example.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeDAO){

        employeeRepository = theEmployeeDAO;
    }
    @Override
    public List<EmployeeDetails> findAll() {

        return employeeRepository.findAll();
    }

    @Override
    public EmployeeDetails findById(Long theId) {
        Optional<EmployeeDetails> result = employeeRepository.findById(theId);
        EmployeeDetails theEmployeeDetails = null;
        if(result.isPresent()){
            theEmployeeDetails = result.get();
        }else{
            throw new EmployeeNotFoundException("Did not find emp id "+ theId);
        }
        return theEmployeeDetails;
    }
    @Override
    public EmployeeDetails save(EmployeeDetails employeeDetails) {

        return employeeRepository.save(employeeDetails);
    }
    @Override
    public EmployeeDetails deleteById(Long theId) {
        employeeRepository.deleteById(theId);
        return null;
    }
}
