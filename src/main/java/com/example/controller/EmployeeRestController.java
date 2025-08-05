package com.example.controller;

import com.example.dto.EmployeeResponse;
import com.example.entity.EmployeeDetails;
import com.example.mapper.EmployeeMapper;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private ObjectMapper objectMapper;
    private EmployeeService employeeService;
    @Autowired
    public  EmployeeRestController(EmployeeService theEmployeeService, ObjectMapper theObjectMapper){
        employeeService=theEmployeeService;
        objectMapper=theObjectMapper;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        List<EmployeeDetails> employees = employeeService.findAll();
        return employees.stream()
                .map(emp -> EmployeeMapper.toDto(emp))
                .collect(Collectors.toList());
    }
    @GetMapping("/{employeeId}")
    public EmployeeResponse getEmployee(@PathVariable long employeeId){
        EmployeeDetails theEmployeeDetails = employeeService.findById(employeeId);
        if(theEmployeeDetails ==null){
            throw new RuntimeException("Employee id not found "+employeeId);
        }
        return EmployeeMapper.toDto(theEmployeeDetails);
    }
    @PostMapping
    public EmployeeDetails addEmployee(@Valid @RequestBody EmployeeDetails theEmployeeDetails){
        theEmployeeDetails.setId(0);
        EmployeeDetails dbEmp = employeeService.save(theEmployeeDetails);
        return dbEmp;
    }
    @PutMapping
    public EmployeeDetails updateEmployee(@RequestBody EmployeeDetails theEmployeeDetails){
        EmployeeDetails dbEmp = employeeService.save(theEmployeeDetails);
        return dbEmp;
    }
    @PatchMapping("/{employeeId}")
    public EmployeeDetails Employee(@PathVariable long employeeId, @RequestBody Map<String,Object> patchPayload){
        EmployeeDetails temp = employeeService.findById(employeeId);
        if(temp == null){
            throw new RuntimeException("Emp id not found "+temp);
        }
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in request body"+employeeId);
        }
        EmployeeDetails patchedEmployeeDetails = apply(patchPayload,temp);
        EmployeeDetails dbEmp = employeeService.save(patchedEmployeeDetails);
        return dbEmp;
    }

    private EmployeeDetails apply(Map<String, Object> patchPayload, EmployeeDetails temp) {
        //Convert emp object to a JSON obj Node
        ObjectNode employeeNode = objectMapper.convertValue(temp,ObjectNode.class);
        // Convert the patchpayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload,ObjectNode.class);
        // merge the patch updates into the emp node
        employeeNode.setAll(patchNode);
        return objectMapper.convertValue(employeeNode, EmployeeDetails.class);
    }
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId){
        EmployeeDetails tempEmployeeDetails = employeeService.findById(employeeId);
        if(tempEmployeeDetails ==null){
            throw new RuntimeException("Employee id not found "+employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id "+employeeId;
    }

}
