package com.example.Hotel_ERP.GuestManagment.ServiceOrder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceOrderRepo extends JpaRepository<ServiceOrder, UUID> {
}
