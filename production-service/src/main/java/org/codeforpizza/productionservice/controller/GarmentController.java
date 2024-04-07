package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.Garment;
import org.codeforpizza.productionservice.modell.GarmentDto;
import org.codeforpizza.productionservice.service.GarmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/garments")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class GarmentController {

    private final GarmentService garmentService;

    @PostMapping("")
    public ResponseEntity<String> createGarment(@Valid @RequestBody GarmentDto garment, Principal principal, Long costumeId) {
        try {
            log.info("Creating garment");
            return garmentService.createGarment(garment, principal, costumeId);
        } catch (Exception e) {
            log.error("Error creating garment", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGarment(@PathVariable Long id, Principal principal, @Valid @RequestBody GarmentDto garmentDto) {
        try {
            log.info("Updating garment");
            return garmentService.updateGarment(id, principal, garmentDto);
        } catch (Exception e) {
            log.error("Error updating garment", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGarment(@PathVariable Long id, Principal principal) {
        try {
            log.info("Deleting garment");
            return garmentService.deleteGarment(id, principal);
        } catch (Exception e) {
            log.error("Error deleting garment", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garment> getGarment(@PathVariable Long id, Principal principal) {
        try {
            log.info("Getting garment");
            return garmentService.getGarment(id, principal);
        } catch (Exception e) {
            log.error("Error getting garment", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Garment>> getAllGarments(Principal principal, Long costumeId) {
        try {
            log.info("Getting all garments");
            return garmentService.getAllGarments(principal, costumeId);
        } catch (Exception e) {
            log.error("Error getting all garments", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
