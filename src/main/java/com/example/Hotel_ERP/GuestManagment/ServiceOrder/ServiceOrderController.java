package com.example.Hotel_ERP.GuestManagment.ServiceOrder;

import com.example.Hotel_ERP.GuestManagment.Maintenance.Maintenance;
import com.example.Hotel_ERP.GuestManagment.Maintenance.MaintenanceDto;
import com.example.Hotel_ERP.GuestManagment.Maintenance.MaintenanceService;
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
@RequestMapping("/api/service-order")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @GetMapping("/get-all-service-orders")
    public ResponseEntity<GlobalResponse<List<ServiceOrder>>> getAllServiceOrders () {
        List<ServiceOrder> serviceOrders = serviceOrderService.getAllServiceOrders();
        return new ResponseEntity<>(new GlobalResponse<>(serviceOrders), HttpStatus.OK);
    }

    @GetMapping("/get-service-order-by-id/{serviceOrderId}")
    public ResponseEntity<GlobalResponse<ServiceOrder>> getServiceOrderById (@PathVariable UUID serviceOrderId) {
        ServiceOrder serviceOrder = serviceOrderService.getServiceOrderById(serviceOrderId);
        return new ResponseEntity<>(new GlobalResponse<>(serviceOrder), HttpStatus.OK);
    }

    @PostMapping("/create-service-order")
    public ResponseEntity<GlobalResponse<String>> createServiceOrder (@Valid @RequestBody ServiceOrderDto.CreateServiceOrder createServiceOrder) {
        serviceOrderService.createServiceOrder(createServiceOrder);
        return new ResponseEntity<>(new GlobalResponse<>("Service order created successful!"), HttpStatus.OK);
    }
}
