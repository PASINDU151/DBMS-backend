package com.backend.service;

import com.backend.model.Employee;
import com.backend.exception.EmployeeAlreadyExistsException;
import com.backend.exception.EmployeeNotFoundException;

import java.util.List;

public interface IEmployee_service {
    Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException;
    List<Employee> getEmployee();
    Employee updateEmployee(Employee employee, Long id) throws EmployeeNotFoundException;
    Employee getEmployeeById(Long id) throws EmployeeNotFoundException;
    void deleteEmployee(Long id) throws EmployeeNotFoundException;
}
