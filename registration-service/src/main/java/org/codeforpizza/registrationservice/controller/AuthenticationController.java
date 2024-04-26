package org.codeforpizza.registrationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.registrationservice.models.DTOs.LoginResponseDTO;
import org.codeforpizza.registrationservice.models.DTOs.RegistationAndUpdateDTO;
import org.codeforpizza.registrationservice.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is the controller for the authentication. it lets the user register and login.
 */

@RestController
@RequestMapping("/registration-api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistationAndUpdateDTO body){
        log.info("Registering user");
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity <LoginResponseDTO> loginUser(@RequestBody RegistationAndUpdateDTO body){
        log.info("Logging in user");
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}

