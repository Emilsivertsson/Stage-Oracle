package org.codeforpizza.productionservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.DTOs.ApplicationUser;
import org.codeforpizza.productionservice.modell.DTOs.RegistationAndUpdateDTO;
import org.codeforpizza.productionservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class AdminController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity <Optional <ApplicationUser>> getOneUser (@PathVariable Long id){
        try {
            return userService.getOneUser(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity <List<ApplicationUser>> getAllUsers () {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <ApplicationUser> updateUser (@PathVariable Long id, @RequestBody RegistationAndUpdateDTO user) {
        try {
            return userService.updateUser(id, user);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteUser (@PathVariable Long id) {
        try {
            return userService.deleteUser(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}