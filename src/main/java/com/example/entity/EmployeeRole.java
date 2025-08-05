package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="employee_roles")
public class EmployeeRole {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String role;
    private String department;
    private String level;
    public EmployeeRole(){}

    public EmployeeRole(String level, String department, String role) {
        this.level = level;
        this.department = department;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
