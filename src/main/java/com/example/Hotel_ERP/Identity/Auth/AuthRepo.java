package com.example.Hotel_ERP.Identity.Auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepo extends JpaRepository<Auth, UUID> {
    Optional<Auth> findByUsername (String username);
}
