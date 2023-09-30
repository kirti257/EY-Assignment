package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ey.model.Employee;
import com.ey.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
	
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/find")
    public List<Employee> searchByAddressStartingWith(@RequestParam String letter) {
        return employeeRepository.findByAddressStartingWith(letter);
    }
}
