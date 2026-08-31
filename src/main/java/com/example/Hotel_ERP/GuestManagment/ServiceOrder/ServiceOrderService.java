package com.example.Hotel_ERP.GuestManagment.ServiceOrder;

import com.example.Hotel_ERP.Accommodation.Room.Room;
import com.example.Hotel_ERP.Accommodation.Room.RoomRepo;
import com.example.Hotel_ERP.Booking.Reservation.Reservation;
import com.example.Hotel_ERP.Booking.Reservation.ReservationRepo;
import com.example.Hotel_ERP.GuestManagment.Maintenance.Maintenance;
import com.example.Hotel_ERP.GuestManagment.Maintenance.MaintenanceDto;
import com.example.Hotel_ERP.GuestManagment.Maintenance.MaintenanceRep;
import com.example.Hotel_ERP.Shared.ErrorHandling.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceOrderService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private ServiceOrderRepo serviceOrderRepo;

    public List<ServiceOrder> getAllServiceOrders () {
        List<ServiceOrder> serviceOrders = serviceOrderRepo.findAll();
        return serviceOrders;
    }

    public ServiceOrder getServiceOrderById (UUID serviceOrderId) {
        ServiceOrder findServiceOrder = serviceOrderRepo.findById(serviceOrderId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(serviceOrderId));
        return findServiceOrder;
    }

    public void createServiceOrder (ServiceOrderDto.CreateServiceOrder createServiceOrder) {
        Reservation findReservation = reservationRepo.findById(createServiceOrder.reservation())
                .orElseThrow(() -> CustomResponseException.idIsNotFound(createServiceOrder.reservation()));

        ServiceOrder serviceOrder = ServiceOrder.createServiceOrder(createServiceOrder, findReservation);
        serviceOrderRepo.save(serviceOrder);
    }

//    public void updateServiceOrder (ServiceOrderDto.UpdateServiceOrder updateServiceOrder) {
//        ServiceOrder findServiceOrder = serviceOrderRepo.findById(updateServiceOrder.ServiceOrderId())
//                .orElseThrow(() -> CustomResponseException.idIsNotFound(updateServiceOrder.ServiceOrderId()));
//
//        ServiceOrder serviceOrder = ServiceOrder.updateServiceOrder(findServiceOrder, updateServiceOrder);
//        serviceOrderRepo.save(serviceOrder);
//    }
//
//    public void deleteServiceOrder (UUID serviceOrderId) {
//        ServiceOrder findServiceOrder = serviceOrderRepo.findById(serviceOrderId)
//                .orElseThrow(() -> CustomResponseException.idIsNotFound(serviceOrderId));
//        serviceOrderRepo.deleteById(findServiceOrder.getId());
//    }
}
