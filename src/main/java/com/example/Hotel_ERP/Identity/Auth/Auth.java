package com.example.Hotel_ERP.Identity.Auth;

import com.example.Hotel_ERP.Identity.Employee.Employee;
import com.example.Hotel_ERP.Identity.Roles.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auths")
public class Auth implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private Roles role;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;

    public static Auth createAuth (AuthDto.SighUp sighUp, String password, Employee employee) {
        Auth auth = new Auth();
        auth.username = sighUp.username();
        auth.password = password;
        auth.role = Roles.EMPLOYEE;
        auth.employee = employee;
        return auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
