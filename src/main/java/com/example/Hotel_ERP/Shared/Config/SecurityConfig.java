package com.example.Hotel_ERP.Shared.Config;

import com.example.Hotel_ERP.Identity.Auth.UserDetailsServiceImpl;
import com.example.Hotel_ERP.Identity.Roles.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String ADMIN = Roles.ADMIN.name();
    private final String EMPLOYEE = Roles.EMPLOYEE.name();

    UserDetailsServiceImpl userDetailsService;
    JwtAuthFilter jwtAuthFilter;

    public SecurityConfig (UserDetailsServiceImpl userDetailsService, JwtAuthFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .cors(c -> c.disable())
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            //Employee
                            "/api/employee/get-all-employees",
                            "/api/employee/create-employee",

                            //Auth
                            "/api/auth/sigh-up",
                            "/api/auth/sigh-in"
                            ).permitAll()
                            // Employee
                            .requestMatchers(HttpMethod.GET, "/api/employee/get-all-employees").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/employee/get-employee-by-id/{employeeId}").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.POST, "/api/employee/create-employee").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.PUT, "/api/employee/update-employee").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.DELETE, "/api/employee/delete-employee/{employeeId}").hasRole(ADMIN)

                            // Auth
                            .requestMatchers(HttpMethod.GET, "/api/auth/get-all-auths").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.POST, "/api/auth/sigh-up").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.POST, "/api/auth/sigh-in").hasAnyRole(ADMIN, EMPLOYEE)

                            // Room
                            .requestMatchers(HttpMethod.GET, "/api/room/get-all-rooms").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/room/get-room-by-id/{roomId}").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.POST, "/api/room/create-room").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.DELETE, "/api/room/delete-room/{roomId}").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.PUT, "/api/room/update-room").hasRole(ADMIN)

                            // Guest
                            .requestMatchers(HttpMethod.GET, "/api/guest/get-all-guests").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/guest/get-guest-by-id/{guestId}").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.POST, "/api/guest/create-guest").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.DELETE, "/api/guest/delete-guest/{guestId}").hasRole(ADMIN)

                            // Reservation
                            .requestMatchers(HttpMethod.GET, "/api/reservation/get-all-reservations").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/reservation/get-reservation-by-id/{reservationId}").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.POST, "/api/reservation/create-reservation").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.PUT, "/api/reservation/update-reservation").hasRole(ADMIN)

                            // Payment
                            .requestMatchers(HttpMethod.GET, "/api/payment/get-all-payments").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/payment/get-payment-by-id/{paymentId}").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.POST, "/api/payment/create-payment").hasAnyRole(ADMIN, EMPLOYEE)
                            .requestMatchers(HttpMethod.PUT, "/api/payment/update-payment").hasRole(ADMIN)

                            // Maintenance
                            .requestMatchers(HttpMethod.GET, "/api/maintenance/get-all-maintenances").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/maintenance/get-maintenance-by-id/{maintenanceId}").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.POST, "/api/maintenance/create-maintenance").hasAnyRole(ADMIN)
                            .requestMatchers(HttpMethod.DELETE, "/api/maintenance/delete-maintenance/{maintenanceId}").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.PUT, "/api/maintenance/update-maintenance").hasRole(ADMIN)

                            // Service-Order
                            .requestMatchers(HttpMethod.GET, "/api/service-order/get-all-service-orders").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/service-order/get-service-order-by-id/{serviceOrderId}").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.POST, "/api/service-order/create-service-order").hasAnyRole(ADMIN)
                        .anyRequest()
                        .authenticated();
                })
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager(httpSecurity));

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity){
        var authBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(
                passwordEncoder()
        );
        return authBuilder.build();
    }
}