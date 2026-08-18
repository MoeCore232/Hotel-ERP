package com.example.Hotel_ERP.Identity.Employee;

import java.time.LocalDate;
import java.util.UUID;

public class EmployeeDto {

    public record CreateEmployee (
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate,
            LocalDate hireDate,
            String salary,
            String jobTitle
    ) {}

    public record UpdateEmployee (
            UUID employeeId,
            String firstName,
            String lastName,
            String email,
            LocalDate birthDate,
            LocalDate hireDate,
            String salary,
            String jobTitle
    ) {}

}
