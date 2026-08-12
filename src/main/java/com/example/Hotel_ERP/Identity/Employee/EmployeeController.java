package com.example.Hotel_ERP.Identity.Employee;

import com.example.Hotel_ERP.Shared.ErrorHandling.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get-all-employees")
    public ResponseEntity<GlobalResponse<List<Employee>>> getAllEmployees () {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @PostMapping("/create-employee")
    public ResponseEntity<GlobalResponse<String>> createEmployee (@RequestBody EmployeeDto.CreateEmployee createEmployee) {
        employeeService.createEmployee(createEmployee);
        return new ResponseEntity<>(new GlobalResponse<>("Employee created successful!"), HttpStatus.OK);
    }

    @DeleteMapping("/delete-employee/{employeeId}")
    public ResponseEntity<GlobalResponse<String>> deleteEmployee (@PathVariable UUID employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>("Employee created successful"), HttpStatus.OK);
    }

}
