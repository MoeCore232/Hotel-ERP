package com.example.Hotel_ERP.GuestManagment.Maintenance;

import com.example.Hotel_ERP.Accommodation.Room.Room;
import com.example.Hotel_ERP.Accommodation.Room.RoomRepo;
import com.example.Hotel_ERP.Shared.ErrorHandling.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRep maintenanceRep;

    @Autowired
    private RoomRepo roomRepo;

    public List<Maintenance> getAllMaintenances () {
        List<Maintenance> maintenances = maintenanceRep.findAll();
        return maintenances;
    }

    public Maintenance getMaintenanceById (UUID maintenanceId) {
        Maintenance findMaintenance = maintenanceRep.findById(maintenanceId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(maintenanceId));
        return findMaintenance;
    }

    public void createMaintenance (MaintenanceDto.CreateMaintenance createMaintenance) {
        Room findRoom = roomRepo.findById(createMaintenance.roomId())
                .orElseThrow(() -> CustomResponseException.idIsNotFound(createMaintenance.roomId()));

        Maintenance maintenance = Maintenance.createMaintenance(createMaintenance, findRoom);
        maintenanceRep.save(maintenance);
    }

    public void updateMaintenance (MaintenanceDto.UpdateMaintenance updateMaintenance) {
        Maintenance findMaintenance = maintenanceRep.findById(updateMaintenance.maintenanceId())
                .orElseThrow(() -> CustomResponseException.idIsNotFound(updateMaintenance.maintenanceId()));

        Maintenance maintenance = Maintenance.updateMaintenance(findMaintenance, updateMaintenance);
        maintenanceRep.save(maintenance);
    }

    public void deleteMaintenance (UUID maintenanceId) {
        Maintenance findMaintenance = maintenanceRep.findById(maintenanceId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(maintenanceId));
        maintenanceRep.deleteById(findMaintenance.getId());
    }
}
