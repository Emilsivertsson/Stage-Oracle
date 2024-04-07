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



    @PostMapping("")
    public ResponseEntity<String> createPerformer(Long performerId, Principal principal, Long CastId) {
        try {
            return ResponseEntity.ok(performerService.createPerformer(performerId, principal, CastId));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    //TODO might not be used
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerformer(@PathVariable Long id, @Valid @RequestBody PerformerDto performerDto, Principal principal) {
        try {
            return ResponseEntity.ok(performerService.updatePerformer(id, performerDto, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerformer(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(performerService.deletePerformer(id, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Performer> getPerformer(@PathVariable Long id, Principal principal) {
        try {
            return performerService.getPerformer(id, principal);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Performer>> getAllPerformers(Principal principal, Long CastId) {
        try {
            return performerService.getAllPerformers(principal,CastId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
