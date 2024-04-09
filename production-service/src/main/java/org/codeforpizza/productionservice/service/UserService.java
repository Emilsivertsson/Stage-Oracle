package org.codeforpizza.productionservice.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.ApplicationUser;
import org.codeforpizza.productionservice.modell.DTOs.RegistationAndUpdateDTO;
import org.codeforpizza.productionservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            log.info("User found");
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            log.info("User not found");
            throw new UsernameNotFoundException("user is not valid");
        }

    }

    public ResponseEntity<Optional<ApplicationUser>> getOneUser (Long id){
        try{
            if (userRepository.existsById(id)){
                log.info("User found");
                return ResponseEntity.ok(userRepository.findById(id));
            } else {
                log.info("User not found");
                return ResponseEntity.status(204).build();
            }
        } catch (Exception e){
            log.info("Error getting user");
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity <List<ApplicationUser>> getAllUsers () {
        try {
            List<ApplicationUser> users = userRepository.findAll();
            if(users.isEmpty()) {
                log.info("No users found");
                return ResponseEntity.status(204).build();
            } else {
                log.info("Users found");
                return ResponseEntity.ok(users);
            }
        } catch (Exception e) {
            log.info("Error getting users");
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        try {
            if(id == 1){
                log.info("Cannot delete admin");
                return ResponseEntity.status(400).body("Cannot delete admin");
            }
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                log.info("User deleted successfully");
                return ResponseEntity.ok("User deleted successfully");
            } else {
                log.info("User not found");
                return ResponseEntity.status(204).build();
            }
        } catch (Exception e) {
            log.info("Error deleting User");
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<ApplicationUser> updateUser(Long id, RegistationAndUpdateDTO user) {
        try {
            ApplicationUser userToUpdate = userRepository.findById(id).get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(encoder.encode(user.getPassword()));
            log.info("User updated successfully");
            return ResponseEntity.ok(userRepository.save(userToUpdate));
        } catch (Exception e) {
            log.info("Error updating user");
            return ResponseEntity.status(400).build();
        }
    }
}
