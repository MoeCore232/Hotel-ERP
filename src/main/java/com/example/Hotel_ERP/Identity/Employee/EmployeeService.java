package com.example.Hotel_ERP.Identity.Employee;

import com.example.Hotel_ERP.Shared.ErrorHandling.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees () {
        return employeeRepo.findAll();
    }

    public void createEmployee (EmployeeDto.CreateEmployee createEmployee) {
        Optional<Employee> findEmployee = employeeRepo.findByEmail(createEmployee.email());
        if (findEmployee.isPresent()) {
            throw CustomResponseException.duplicateItem("email");
        }
        Employee employee = Employee.createEmployee(createEmployee);
        employeeRepo.save(employee);
    }

    public void deleteEmployee (UUID employeeId) {
        Employee findEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(employeeId));
        employeeRepo.deleteById(findEmployee.getId());
    }

}
