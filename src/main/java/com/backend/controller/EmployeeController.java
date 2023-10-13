package com.backend.controller;


import com.backend.exception.EmployeeAlreadyExistsException;
import com.backend.exception.EmployeeNotFoundException;
import com.backend.model.Employee;
import com.backend.service.IEmployee_service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/Employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployee_service employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployee(){
        return new ResponseEntity<>(employeeService.getEmployee(), HttpStatus.FOUND);
    }
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
        return employeeService.addEmployee(employee);
    }
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) throws EmployeeNotFoundException{
        return employeeService.updateEmployee(employee, id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException{
        employeeService.deleteEmployee(id);
    }
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(id);
    }
}


