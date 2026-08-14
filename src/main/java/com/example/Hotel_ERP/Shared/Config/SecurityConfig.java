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
        httpSecurity.
                cors(c -> c.disable())
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                        //Employee
                        "/api/employee/create-employee",

                        //Auth
                        "/api/auth/sigh-up",
                        "/api/auth/sigh-in"
                        ).permitAll()
                        //Employee
                        .requestMatchers(HttpMethod.GET, "/api/employee/get-all-employees").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/employee/create-employee").hasAnyRole(ADMIN, EMPLOYEE)

                        //Auth
                        .requestMatchers(HttpMethod.GET, "/api/auth/get-all-auths").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/auth/sigh-up").hasAnyRole(ADMIN, EMPLOYEE)
                        .requestMatchers(HttpMethod.POST, "/api/auth/sigh-in").hasAnyRole(ADMIN, EMPLOYEE)

                        //Room
                        .requestMatchers(HttpMethod.GET, "/api/room/get-all-rooms").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/room/create-room").hasAnyRole(ADMIN, EMPLOYEE)
                        .requestMatchers(HttpMethod.DELETE, "/api/room/delete-room/{roomId}").hasRole(ADMIN)

                        //Guest
                        .requestMatchers(HttpMethod.GET, "/api/guest/get-all-guests").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/guest/create-guest").hasAnyRole(ADMIN, EMPLOYEE)
                        .requestMatchers(HttpMethod.DELETE, "/api/guest/delete-guest/{guestId}").hasRole(ADMIN)

                        //Reservation
                        .requestMatchers(HttpMethod.GET, "/api/reservation/get-all-reservations").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/reservation/create-reservation").hasAnyRole(ADMIN, EMPLOYEE)
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