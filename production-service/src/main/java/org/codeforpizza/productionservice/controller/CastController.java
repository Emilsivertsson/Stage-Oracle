package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.CastDto;
import org.codeforpizza.productionservice.service.CastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/casts")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CastController {

    private final CastService castService;

    @PostMapping("")
    public ResponseEntity<String> createCast(@Valid @RequestBody CastDto castDto, Principal principal) {
        try {
            return ResponseEntity.ok(castService.createCast(castDto, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCast(@PathVariable Long id, Principal principal, @Valid @RequestBody CastDto castDto) {
        try {
            return ResponseEntity.ok(castService.updateCast(id, principal, castDto));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCast(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(castService.deleteCast(id, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCast(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(castService.getCast(id, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getAllCasts(Principal principal) {
        try {
            return ResponseEntity.ok(castService.getAllCasts(principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

}
