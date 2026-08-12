package com.example.Hotel_ERP.Identity.Auth;

import com.example.Hotel_ERP.Identity.Employee.Employee;
import com.example.Hotel_ERP.Identity.Employee.EmployeeRepo;
import com.example.Hotel_ERP.Shared.Config.JwtHelper;
import com.example.Hotel_ERP.Shared.ErrorHandling.CustomResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;

    public List<Auth> getAllAuths () {
        return authRepo.findAll();
    }

    @Transactional
    public AuthDto.LoginResponse sighUp(AuthDto.SighUp sighUp){
        Employee findEmployee = employeeRepo.findById(sighUp.userId())
                .orElseThrow(() -> CustomResponseException.idIsNotFound(sighUp.userId()));

        Optional<Auth> findEmail = authRepo.findByUsername(sighUp.username());
        if (findEmail.isPresent()) {
            throw CustomResponseException.duplicateItem("email");
        }
        try {
            String password = passwordEncoder.encode(sighUp.password());
            Auth auth = Auth.createAuth(sighUp, password, findEmployee);
            authRepo.save(auth);

            String token = jwtHelper.generateToken(auth);
            return new AuthDto.LoginResponse(token, findEmployee.getId(), "Sighup Successful!");
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
            e.printStackTrace();
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    @Transactional
    public AuthDto.LoginResponse sighIn(AuthDto.Login login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.username(),
                        login.password()
                ));
        Auth findAuth = authRepo.findByUsername(login.username())
                .orElseThrow(() -> CustomResponseException.badCredentials());
        try {
            String token = jwtHelper.generateToken(findAuth);
            return new AuthDto.LoginResponse(token, findAuth.getId(), "Login Successful!");
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }
}
