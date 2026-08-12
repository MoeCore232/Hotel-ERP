package com.example.Hotel_ERP.GuestManagment.Guest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guests")
public class Guest {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birth_date", nullable = false, updatable = false)
    private LocalDate birthDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Guest createGuest (GuestDto.CreateGuest createGuest) {
        Guest guest = new Guest();
        guest.firstName = createGuest.firstName();
        guest.lastName = createGuest.lastName();
        guest.email = createGuest.email();
        guest.birthDate = createGuest.birthDate();
        return guest;
    }
}
