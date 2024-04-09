package org.codeforpizza.productionservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.DTOs.IsAuthenticatedDTO;
import org.codeforpizza.productionservice.modell.DTOs.LoginResponseDTO;
import org.codeforpizza.productionservice.modell.DTOs.RegistationAndUpdateDTO;
import org.codeforpizza.productionservice.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is the controller for the authentication
 * It is responsible for handling the requests and responses for the authentication routes
 */

@RestController
@RequestMapping("/auth")
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

    @GetMapping("/isAuthenticated")
    public ResponseEntity<Boolean> isAuthenticated(@RequestBody IsAuthenticatedDTO username){
        log.info("Checking if user is authenticated");
        return authenticationService.isAuthenticated(username);
    }
}

