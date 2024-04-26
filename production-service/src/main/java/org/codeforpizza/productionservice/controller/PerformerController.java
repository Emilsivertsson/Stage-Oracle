package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.DTOs.GetPerformerRequestDTO;
import org.codeforpizza.productionservice.modell.DTOs.PerformerResponsDTO;
import org.codeforpizza.productionservice.modell.entitys.Performer;
import org.codeforpizza.productionservice.modell.DTOs.PerformerDto;
import org.codeforpizza.productionservice.service.PerformerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/production-api/performers")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PerformerController {

    private final PerformerService performerService;

    /**
     * Create a performer in the production-service, this sends a request to the registry-service
     * @param getPerformerRequestDTO
     * @param castId
     * @return ResponseEntity<String>
     */

    @PostMapping("/{castId}")
    public ResponseEntity<String> createPerformer(@RequestBody GetPerformerRequestDTO getPerformerRequestDTO, @PathVariable Long castId) {
        try {
            log.info("received request to create performer");
            return performerService.createPerformer(getPerformerRequestDTO, castId);
        } catch (Exception e) {
            log.info("failed to recive request to create performer");
            log.info(e.getMessage() + " " + e.getCause());
            return ResponseEntity.status(400).build();
        }
    }

    //TODO Not used, but might be in the future
    @PutMapping("/{performerId}")
    public ResponseEntity<String> updatePerformer(@PathVariable Long performerId, @Valid @RequestBody PerformerDto performerDto) {
        try {
            return performerService.updatePerformer(performerId, performerDto);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{performerId}")
    public ResponseEntity<String> deletePerformer(@PathVariable Long performerId) {
        try {
            return performerService.deletePerformer(performerId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{performerId}")
    public ResponseEntity<Performer> getPerformer(@PathVariable Long performerId ) {
        try {
            return performerService.getPerformer(performerId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/cast/{castId}")
    public ResponseEntity<List<Performer>> getAllPerformers(@PathVariable Long castId) {
        try {
            return performerService.getAllPerformers(castId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    /**
     * Get all performers from the registry, this re-directs a request to the registry-service
     * @param castId
     * @return List of PerformerResponsDTO
     */

    @GetMapping("/registry/{castId}")
    public ResponseEntity<List<PerformerResponsDTO>> getAllPerformersFromRegistry(@PathVariable Long castId ) {
        try {
            return performerService.getAllPerformersFromRegistry(castId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }
}
