package com.backend.service;

import com.backend.model.Employee;
import com.backend.repository.EmployeeRepository;
import com.backend.exception.EmployeeAlreadyExistsException;
import com.backend.exception.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployee_service{

    private final EmployeeRepository employeeRepository;
    @Override
    public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        if (EmployeeAlreadyExists(employee.getEmail())){
            throw new EmployeeAlreadyExistsException(employee.getEmail()+"already exists!");
        }
        return employeeRepository.save(employee);
    }



    @Override
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id).map(st -> {
            st.setFirst_name(employee.getFirst_name());
            st.setLast_name(employee.getLast_name());
            st.setEmail(employee.getEmail());
            st.setDepartment(employee.getDepartment());
            return employeeRepository.save(st);
        }).orElseThrow(() -> new EmployeeNotFoundException("Sorry, this student could not be found"));
    }

    @Override
    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Sorry, no Employee found with the Id :" +id));
    }

    @Override
    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException("Sorry, Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    private boolean EmployeeAlreadyExists(String email) {
        return employeeRepository.findByEmail(email).isPresent();
    }
}

