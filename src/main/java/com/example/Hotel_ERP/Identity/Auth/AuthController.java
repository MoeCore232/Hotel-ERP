package com.example.Hotel_ERP.Identity.Auth;

import com.example.Hotel_ERP.Shared.ErrorHandling.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController (AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/get-all-auths")
    public ResponseEntity<GlobalResponse<List<Auth>>> getAllAuths () {
        List<Auth> auths = authService.getAllAuths();
        return new ResponseEntity<>(new GlobalResponse<>(auths), HttpStatus.OK);
    }

    @PostMapping("/sigh-up")
    public ResponseEntity<GlobalResponse<AuthDto.LoginResponse>> sighUp(@Valid @RequestBody AuthDto.SighUp sighUp){
        AuthDto.LoginResponse response = authService.sighUp(sighUp);
        return new ResponseEntity<>(new GlobalResponse<>(response), HttpStatus.OK);
    }

    @PostMapping("/sigh-in")
    public ResponseEntity<GlobalResponse<AuthDto.LoginResponse>> sighIn(@Valid @RequestBody AuthDto.Login login) {
        AuthDto.LoginResponse response = authService.sighIn(login);
        return new ResponseEntity<>(new GlobalResponse<>(response), HttpStatus.OK);
    }
}
