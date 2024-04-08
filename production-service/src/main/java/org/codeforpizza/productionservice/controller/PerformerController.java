package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.Performer;
import org.codeforpizza.productionservice.modell.PerformerDto;
import org.codeforpizza.productionservice.service.PerformerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/performers")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PerformerController {

    private final PerformerService performerService;



    @PostMapping("/{castId}/getPerformer/{performerId}")
    public ResponseEntity<String> createPerformer(@PathVariable Long performerId, Principal principal,@PathVariable Long castId) {
        try {
            return ResponseEntity.ok(performerService.createPerformer(performerId, principal, castId));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    //TODO might not be used
    @PutMapping("/{performerId}")
    public ResponseEntity<String> updatePerformer(@PathVariable Long performerId, @Valid @RequestBody PerformerDto performerDto, Principal principal) {
        try {
            return ResponseEntity.ok(performerService.updatePerformer(performerId, performerDto, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{performerId}")
    public ResponseEntity<String> deletePerformer(@PathVariable Long performerId, Principal principal) {
        try {
            return ResponseEntity.ok(performerService.deletePerformer(performerId, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{performerId}")
    public ResponseEntity<Performer> getPerformer(@PathVariable Long performerId, Principal principal) {
        try {
            return performerService.getPerformer(performerId, principal);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/cast/{castId}")
    public ResponseEntity<List<Performer>> getAllPerformers(Principal principal,@PathVariable Long castId) {
        try {
            return performerService.getAllPerformers(principal,castId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
