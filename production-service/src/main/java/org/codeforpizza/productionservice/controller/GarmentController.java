package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.Garment;
import org.codeforpizza.productionservice.modell.DTOs.GarmentDto;
import org.codeforpizza.productionservice.service.GarmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/production-api/garments")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class GarmentController {

    private final GarmentService garmentService;

    @PostMapping("/{costumeId}")
    public ResponseEntity<String> createGarment(@Valid @RequestBody GarmentDto garment,@PathVariable Long costumeId) {
        try {
            log.info("Creating garment");
            return garmentService.createGarment(garment, costumeId);
        } catch (Exception e) {
            log.error("Error creating garment", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/garmentStatus/{garmentId}")
    public ResponseEntity<String> updateGarmentStatus(@PathVariable Long garmentId) {
        try {
            log.info("Updating garment status");
            return garmentService.updateGarmentStatus(garmentId);
        } catch (Exception e) {
            log.error("Error updating garment status", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{garmentId}")
    public ResponseEntity<String> updateGarment(@PathVariable Long garmentId, @Valid @RequestBody GarmentDto garmentDto) {
        try {
            log.info("Updating garment");
            return garmentService.updateGarment(garmentId, garmentDto);
        } catch (Exception e) {
            log.error("Error updating garment", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{garmentId}")
    public ResponseEntity<String> deleteGarment(@PathVariable Long garmentId) {
        try {
            log.info("Deleting garment");
            return garmentService.deleteGarment(garmentId);
        } catch (Exception e) {
            log.error("Error deleting garment", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{garmentId}")
    public ResponseEntity<Garment> getGarment(@PathVariable Long garmentId) {
        try {
            log.info("Getting garment");
            return garmentService.getGarment(garmentId);
        } catch (Exception e) {
            log.error("Error getting garment", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/costume/{costumeId}")
    public ResponseEntity<List<Garment>> getAllGarments(@PathVariable Long costumeId) {
        try {
            log.info("Getting all garments");
            return garmentService.getAllGarments(costumeId);
        } catch (Exception e) {
            log.error("Error getting all garments", e);
            return ResponseEntity.badRequest().build();
        }
    }



}
