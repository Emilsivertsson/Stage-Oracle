package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.ApplicationUser;
import org.codeforpizza.productionservice.modell.DTOs.IsAuthenticatedDTO;
import org.codeforpizza.productionservice.modell.DTOs.LoginResponseDTO;
import org.codeforpizza.productionservice.modell.entitys.AwanChats;
import org.codeforpizza.productionservice.modell.entitys.Production;
import org.codeforpizza.productionservice.modell.entitys.Role;
import org.codeforpizza.productionservice.repository.ProductionRepository;
import org.codeforpizza.productionservice.repository.RoleRepository;
import org.codeforpizza.productionservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
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


    public ResponseEntity<String> registerUser(String username, String password) {
        try{
        if(userRepository.existsByUsername(username)){
            log.error("User already exists");
            return ResponseEntity.status(409).body("User already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        log.info("User role created");

        List<Production> productions = List.of();
        List<AwanChats> chats = List.of();

        ApplicationUser newUser = new ApplicationUser(username, encodedPassword, authorities,productions,chats);
        userRepository.save(newUser);
        log.info("User registered successfully");

        return ResponseEntity.ok("User registered successfully");

        } catch (Exception e){
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

    public ResponseEntity<Boolean> isAuthenticated(IsAuthenticatedDTO username) {
        try {
            if (userRepository.existsByUsername(username.getUsername())) {
                log.info("User is authenticated");
                return ResponseEntity.ok(true);
            } else {
                log.info("User is not authenticated");
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            log.error("Error checking if user is authenticated");
            return ResponseEntity.badRequest().build();
        }
    }
}

