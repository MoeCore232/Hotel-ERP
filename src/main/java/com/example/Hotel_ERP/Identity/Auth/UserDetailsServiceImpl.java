package com.example.Hotel_ERP.Identity.Auth;

import com.example.Hotel_ERP.Shared.ErrorHandling.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Auth> findUserAccount = authRepo.findByUsername(username);

        if(findUserAccount.isEmpty()){
            throw CustomResponseException.BadCredentials();
        }

        Auth userAccount = findUserAccount.get();
        return User.builder()
                .username(userAccount.getUsername())
                .password(userAccount.getPassword())
                .roles(userAccount.getRole().name())
                .build();
    }
}
