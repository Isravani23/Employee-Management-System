package com.example.controller;

import com.example.entity.EmployeeDetails;
import com.example.entity.KYCdetails;
import com.example.repository.EmployeeRepository;
import com.example.repository.KYCRepository;
import com.example.service.EmployeeService;
import com.example.service.KYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeePageController {
    private final EmployeeRepository employeeRepository;
    private final KYCRepository kycRepository;
    private final EmployeeService employeeService;
    private final KYCService kycService;

    @Autowired
    public EmployeePageController(EmployeeRepository employeeRepository,
                                  KYCRepository kycRepository,
                                  EmployeeService employeeService,
                                  KYCService kycService) {
        this.employeeRepository = employeeRepository;
        this.kycRepository = kycRepository;
        this.employeeService = employeeService;
        this.kycService = kycService;
    }

    @GetMapping
    public String employeesHome() {
        return "employees";  // employees.html
    }

    // Show Add Employee Form (add-employee.html)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeDetails());
        return "add-employee";
    }

    // Save Employee (form submission)
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute EmployeeDetails employee,Model model) {
        EmployeeDetails savedEmployee = employeeRepository.save(employee);
        model.addAttribute("employee", savedEmployee);
        return "success";
    }
    @GetMapping("/kyc/add/{employeeId}")
    public String showKycForm(@PathVariable("employeeId") Long employeeId, Model model) {
        EmployeeDetails employee = employeeRepository.findById(employeeId).orElseThrow();
        KYCdetails kyc = new KYCdetails();
        kyc.setEmployee(employee);   // assuming relation like private EmployeeDetails employee;
        model.addAttribute("kyc", kyc);
        return "kyc-form";
    }

    @PostMapping("/kyc/save")
    public String saveKycDetails(@ModelAttribute("kyc") KYCdetails kyc, Model model) {
        kycRepository.save(kyc);
        model.addAttribute("kyc", kyc);
        return "kycSuccess";
    }



    // Show All Employees (list-employees.html)
    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<EmployeeDetails> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees-list";
    }

    // Show Update Form
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        EmployeeDetails employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") EmployeeDetails employee) {
        EmployeeDetails existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setEmploymentType(employee.getEmploymentType());
        existing.setWorkLocation(employee.getWorkLocation());
        existing.setJoiningDate(employee.getJoiningDate());

        employeeRepository.save(existing);
        return "redirect:/employees/list";
    }

    // Delete Employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees/list";
    }
}
