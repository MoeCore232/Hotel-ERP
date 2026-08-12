package com.example.Hotel_ERP.Identity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByEmail (String email);
}
