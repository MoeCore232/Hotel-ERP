package com.example.Hotel_ERP.GuestManagment.Guest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuestRepo extends JpaRepository<Guest, UUID> {
}
