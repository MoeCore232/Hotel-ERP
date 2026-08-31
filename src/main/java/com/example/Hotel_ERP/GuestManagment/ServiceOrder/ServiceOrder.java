package com.example.Hotel_ERP.GuestManagment.ServiceOrder;

import com.example.Hotel_ERP.Booking.Reservation.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service-orders")
public class ServiceOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation", nullable = false)
    private Reservation reservation;

    @Column(name = "hotel_service", nullable = false)
    private HotelService hotelService;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public static ServiceOrder createServiceOrder (ServiceOrderDto.CreateServiceOrder createServiceOrder, Reservation reservation) {
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.reservation = reservation;
        serviceOrder.hotelService = createServiceOrder.hotelService();
        serviceOrder.quantity = createServiceOrder.quantity();
        serviceOrder.totalPrice = createServiceOrder.totalPrice();
        return serviceOrder;
    }

    public static ServiceOrder updateServiceOrder (ServiceOrder serviceOrder, ServiceOrderDto.UpdateServiceOrder updateServiceOrder) {
        serviceOrder.hotelService = updateServiceOrder.hotelService();
        serviceOrder.quantity = updateServiceOrder.quantity();
        serviceOrder.totalPrice = updateServiceOrder.totalPrice();
        return serviceOrder;
    }
}
