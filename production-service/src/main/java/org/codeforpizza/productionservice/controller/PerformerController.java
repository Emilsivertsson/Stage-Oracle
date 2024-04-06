package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.PerformerDto;
import org.codeforpizza.productionservice.service.PerformerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/performers")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PerformerController {

    private final PerformerService performerService;


    //TODO Get performer from other service, based on the id
    @PostMapping("")
    public ResponseEntity<String> createPerformer(@Valid @RequestBody PerformerDto performerDto, Principal principal) {
        try {
            return ResponseEntity.ok(performerService.createPerformer(performerDto, principal));
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
    public ResponseEntity<String> getPerformer(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(performerService.getPerformer(id, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getAllPerformers(Principal principal) {
        try {
            return ResponseEntity.ok(performerService.getAllPerformers(principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
