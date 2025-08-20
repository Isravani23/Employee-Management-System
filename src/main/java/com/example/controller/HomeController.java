package com.example.controller;

import com.example.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // Root URL → index.html
    @GetMapping("/EMS")
    public String home() {
        return "index"; // this corresponds to src/main/resources/templates/index.html
    }
}
