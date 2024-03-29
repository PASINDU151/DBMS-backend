package com.Jupiter.hrm.service;
import com.Jupiter.hrm.entity.Employee;
import com.Jupiter.hrm.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {

    private ModelMapper modelMapper;
    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper){
        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
    }

    public Long createEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees(){return  employeeRepo.findAllEmployees();}

    public Employee getEmployeeById(int id){
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        return optionalEmployee.orElse(null);
    }

    public boolean updateEmployee(int id, Employee updatedEmployee) {
        updatedEmployee.setEmployee_id(id);
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employeeRepo.update(updatedEmployee);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deleteEmployee(int id){
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isPresent()){
            employeeRepo.delete(optionalEmployee.get());
            return true;
        }
        else{
            return false;
        }
    }


}
