package com.example.controller;

import com.example.entity.EmployeeRole;
import com.example.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private RoleService roleService;
    @Autowired
    public RoleController(RoleService theroleService) {

        this.roleService = theroleService;
    }
    @GetMapping
    public List<EmployeeRole> findAll(){
    return roleService.findAll();
    }
    @GetMapping("/{id}")
    public EmployeeRole getRole(@PathVariable int id){
        EmployeeRole theemployeeRole = roleService.findById(id);
        if(theemployeeRole==null){
            throw new RuntimeException("Role Id not found"+id);
        }
        return theemployeeRole;
    }
    @PostMapping
    public EmployeeRole createRole(@Valid @RequestBody EmployeeRole theEmployeeRole){
        return roleService.save(theEmployeeRole);
    }
    @DeleteMapping("/{id}")
    public EmployeeRole deleteRole(@PathVariable int id){
        EmployeeRole tempRole = roleService.deleteById(id);
        if(tempRole==null){
            throw new RuntimeException("Role Id not found"+id);
        }
        return tempRole;
    }
}
