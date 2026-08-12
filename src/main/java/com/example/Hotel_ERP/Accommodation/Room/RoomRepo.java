package com.example.Hotel_ERP.Accommodation.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepo extends JpaRepository<Room, UUID> {
}
