package com.example.Hotel_ERP.Identity.Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class EmployeeDto {

    public record CreateEmployee (
            @NotBlank(message = "is required!")
            String firstName,
            @NotBlank(message = "is required!")
            String lastName,
            @Email(message = "is required!")
            String email,
            @NotNull(message = "is required!")
            LocalDate birthDate,
            @NotNull(message = "is required!")
            LocalDate hireDate,
            @NotBlank(message = "is required!")
            String salary,
            @NotBlank(message = "is required!")
            String jobTitle
    ) {}

    public record UpdateEmployee (
            UUID employeeId,
            @NotBlank(message = "is required!")
            String firstName,
            @NotBlank(message = "is required!")
            String lastName,
            @Email(message = "is required!")
            String email,
            @NotNull(message = "is required!")
            LocalDate birthDate,
            @NotNull(message = "is required!")
            LocalDate hireDate,
            @NotBlank(message = "is required!")
            String salary,
            @NotBlank(message = "is required!")
            String jobTitle
    ) {}
}
