package com.example.Hotel_ERP.GuestManagment.Maintenance;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class MaintenanceDto {

    public record CreateMaintenance (
            UUID roomId,
            @NotBlank(message = "is required!")
            String description
    ) {}

    public record UpdateMaintenance (
            UUID maintenanceId,
            @NotBlank(message = "is required!")
            String description
    ) {}
}
