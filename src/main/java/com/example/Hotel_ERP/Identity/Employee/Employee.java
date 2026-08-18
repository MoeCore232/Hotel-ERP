package com.example.Hotel_ERP.Identity.Employee;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false, updatable = false)
    private LocalDate birthDate;

    @Column(name = "hire_date", nullable = false, updatable = false)
    private LocalDate hireDate;

    @Column(name = "salary", nullable = false)
    private String salary;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "action", nullable = false)
    private boolean action;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Employee createEmployee (EmployeeDto.CreateEmployee createEmployee) {
        Employee employee = new Employee();
        employee.firstName = createEmployee.firstName();
        employee.lastName = createEmployee.lastName();
        employee.email = createEmployee.email();
        employee.birthDate = createEmployee.birthDate();
        employee.hireDate = createEmployee.hireDate();
        if (createEmployee.hireDate() == null) {
            employee.hireDate = LocalDate.now();
        }
        employee.salary = createEmployee.salary();
        employee.jobTitle = createEmployee.jobTitle();
        employee.action = true;
        return employee;
    }

    public static Employee updateEmployee (Employee employee, EmployeeDto.UpdateEmployee updateEmployee) {
        employee.firstName = updateEmployee.firstName();
        employee.lastName = updateEmployee.lastName();
        employee.email = updateEmployee.email();
        employee.birthDate = updateEmployee.birthDate();
        employee.hireDate = updateEmployee.hireDate();
        employee.salary = updateEmployee.salary();
        employee.jobTitle = updateEmployee.jobTitle();
        return employee;
    }

    public void activeEmployee () {
        this.action = true;
    }

    public void deActiveEmployee () {
        this.action = false;
    }
}
