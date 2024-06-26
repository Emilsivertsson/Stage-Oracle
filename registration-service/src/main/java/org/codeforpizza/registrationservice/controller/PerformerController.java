package org.codeforpizza.registrationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.codeforpizza.registrationservice.models.DTOs.MeasurementsDTO;
import org.codeforpizza.registrationservice.models.entitys.Performer;
import org.codeforpizza.registrationservice.models.DTOs.PerformerDTO;
import org.codeforpizza.registrationservice.service.PerformerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * This class is the controller for the performer. it lets the performers perform CRUD operations on their account.
 */

@RestController
@RequestMapping("/registration-api/performer")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class PerformerController {

    private final PerformerService performerService;

    @GetMapping("")
    public ResponseEntity<Optional<Performer>> getPerformer(Principal principal) {
        try {
            return performerService.getPerformer(principal);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("")
    public ResponseEntity <Performer> updatePerformer(Principal principal, @RequestBody PerformerDTO performer) {
        try {
            return performerService.updatePerformer(principal, performer);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/measurements/")
    public ResponseEntity <Performer> updateMeasurements(Principal principal, @RequestBody MeasurementsDTO measurements) {
        try {
            return performerService.updateMeasurements(principal, measurements);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAccount(Principal principal) {
        try {
            return performerService.deleteAccount(principal);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
