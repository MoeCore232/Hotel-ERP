package com.example.Hotel_ERP.GuestManagment.Maintenance;

import com.example.Hotel_ERP.Shared.ErrorHandling.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("/get-all-maintenances")
    public ResponseEntity<GlobalResponse<List<Maintenance>>> getAllMaintenances () {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
        return new ResponseEntity<>(new GlobalResponse<>(maintenances), HttpStatus.OK);
    }

    @GetMapping("/get-maintenance-by-id/{maintenanceId}")
    public ResponseEntity<GlobalResponse<Maintenance>> getMaintenanceById (@PathVariable UUID maintenanceId) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(maintenanceId);
        return new ResponseEntity<>(new GlobalResponse<>(maintenance), HttpStatus.OK);
    }

    @PostMapping("/create-maintenance")
    public ResponseEntity<GlobalResponse<String>> createMaintenance (@Valid @RequestBody MaintenanceDto.CreateMaintenance createMaintenance) {
        maintenanceService.createMaintenance(createMaintenance);
        return new ResponseEntity<>(new GlobalResponse<>("Maintenance created successful!"), HttpStatus.OK);
    }

    @PutMapping("/update-maintenance")
    public ResponseEntity<GlobalResponse<String>> updateMaintenance (@RequestBody MaintenanceDto.UpdateMaintenance updateMaintenance) {
        maintenanceService.updateMaintenance(updateMaintenance);
        return new ResponseEntity<>(new GlobalResponse<>("Maintenance updated successful"), HttpStatus.OK);
    }

    @DeleteMapping("/delete-maintenance/{maintenanceId}")
    public ResponseEntity<GlobalResponse<String>> deleteMaintenance (@PathVariable UUID maintenanceId) {
        maintenanceService.deleteMaintenance(maintenanceId);
        return new ResponseEntity<>(new GlobalResponse<>("Maintenance deleted successful"), HttpStatus.OK);
    }
}
