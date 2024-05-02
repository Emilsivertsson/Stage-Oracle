package org.codeforpizza.registrationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.codeforpizza.registrationservice.models.DTOs.LoginResponseDTO;
import org.codeforpizza.registrationservice.models.entitys.ApplicationUser;
import org.codeforpizza.registrationservice.models.entitys.Measurements;
import org.codeforpizza.registrationservice.models.entitys.Performer;
import org.codeforpizza.registrationservice.models.entitys.Role;
import org.codeforpizza.registrationservice.repository.MeasurementsRepository;
import org.codeforpizza.registrationservice.repository.PerformerRepository;
import org.codeforpizza.registrationservice.repository.RoleRepository;
import org.codeforpizza.registrationservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;



@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final PerformerRepository performerRepository;

    private final MeasurementsRepository measurementsRepository;


    public ResponseEntity<String> registerUser(String username, String password) {
        try {
            if (userRepository.existsByUsername(username)) {
                log.error("User already exists");
                return ResponseEntity.status(409).body("User already exists");
            }

            String encodedPassword = passwordEncoder.encode(password);
            Role userRole = roleRepository.findByAuthority("USER").get();
            Set<Role> authorities = new HashSet<>();
            authorities.add(userRole);

            Measurements measurements = new Measurements(0, 0, 0, 0, 0);
            measurementsRepository.save(measurements);

            Performer performer = new Performer("", "", "", "", "", measurements);
            performerRepository.save(performer);

            ApplicationUser newUser = new ApplicationUser(username, encodedPassword, authorities, performer);
            userRepository.save(newUser);
            log.info("User registered successfully");
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            log.error("User registration failed");
            return ResponseEntity.status(400).body("User registration failed");
        }
    }

    public ResponseEntity<LoginResponseDTO> loginUser(String username, String password) {
        try {
            if (!userRepository.existsByUsername(username)) {
                log.error("no user found");
                return ResponseEntity.status(404).build();
            }
            ApplicationUser user = userRepository.findByUsername(username);

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.generateJwt(auth);
            LoginResponseDTO loggedUser = new LoginResponseDTO(user.getUserId(),username,token);

            log.info("User logged in successfully");
            return ResponseEntity.ok(loggedUser);

        } catch (AuthenticationException e) {
            log.error("User login failed");
            return ResponseEntity.status(401).build();
        }
    }
}
