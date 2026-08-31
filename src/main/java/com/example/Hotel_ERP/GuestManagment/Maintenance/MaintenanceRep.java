package com.example.Hotel_ERP.GuestManagment.Maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaintenanceRep extends JpaRepository<Maintenance, UUID> {

}
