package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.Cast;
import org.codeforpizza.productionservice.modell.DTOs.CastDto;
import org.codeforpizza.productionservice.service.CastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/casts")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CastController {

    private final CastService castService;

    @PostMapping("/{manifestId}")
    public ResponseEntity<String> createCast(@Valid @RequestBody CastDto castDto, Principal principal,@PathVariable Long manifestId) {
        try {
            log.info("Creating cast in api");
            return castService.createCast(castDto, principal, manifestId);
        } catch (Exception e) {
            log.error("Error creating cast in api");
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{castId}")
    public ResponseEntity<String> updateCast(@PathVariable Long castId, Principal principal, @Valid @RequestBody CastDto castDto) {
        try {
            return castService.updateCast(castId, principal, castDto);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{castId}")
    public ResponseEntity<String> deleteCast(@PathVariable Long castId, Principal principal) {
        try {
            return castService.deleteCast(castId, principal);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{castId}")
    public ResponseEntity<Cast> getCast(@PathVariable Long castId, Principal principal) {
        try {
            return castService.getCast(castId, principal);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/manifest/{ManifestId}")
    public ResponseEntity<List<Cast>> getAllCasts(Principal principal,@PathVariable Long ManifestId) {
        try {
            return castService.getAllCasts(principal, ManifestId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

}
