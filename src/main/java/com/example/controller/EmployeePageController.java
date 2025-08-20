package com.example.controller;

import com.example.entity.EmployeeDetails;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeePageController {
    @Autowired
    private EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    @Autowired
    public EmployeePageController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String employeesHome() {
        return "employees";  // employees.html
    }

    // Show Add Employee Form (add-employee.html)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeDetails());
        return "add-employee";  // add-employee.html
    }

    // Save Employee (form submission)
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute EmployeeDetails employee) {
        employeeRepository.save(employee);
        return "redirect:/employees/list"; // redirect to list page
    }

    // Show All Employees (list-employees.html)
    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<EmployeeDetails> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees-list"; // list-employees.html
    }

    // Show Update Form
    @GetMapping("/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        EmployeeDetails employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "add-employee"; // reuse add-employee.html for update
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") EmployeeDetails employee) {
        // employee.id will have the ID from the hidden field
        employeeRepository.save(employee);
        return "redirect:/employees/list";
    }

    // Delete Employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees/list";
    }
}
