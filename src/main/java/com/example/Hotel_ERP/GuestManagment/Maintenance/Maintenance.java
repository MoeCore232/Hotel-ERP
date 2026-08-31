package com.example.Hotel_ERP.GuestManagment.Maintenance;

import com.example.Hotel_ERP.Accommodation.Room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "maintenances")
public class Maintenance {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "room", nullable = false)
    private Room room;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "maintenance_status", nullable = false)
    private MaintenanceStatus maintenanceStatus;

    public static Maintenance createMaintenance (MaintenanceDto.CreateMaintenance createMaintenance, Room room) {
        Maintenance maintenance = new Maintenance();
        maintenance.room = room;
        maintenance.description = createMaintenance.description();
        maintenance.maintenanceStatus = MaintenanceStatus.PENDING;
        return maintenance;
    }

    public static Maintenance updateMaintenance (Maintenance maintenance, MaintenanceDto.UpdateMaintenance updateMaintenance) {
        maintenance.description = updateMaintenance.description();
        return maintenance;
    }
}
