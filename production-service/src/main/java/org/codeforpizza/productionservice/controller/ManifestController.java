package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.Manifest;
import org.codeforpizza.productionservice.modell.DTOs.ManifestDto;
import org.codeforpizza.productionservice.service.ManifestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/manifests")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ManifestController {

    private final ManifestService manifestService;

    @PostMapping("/{productionId}")
    public ResponseEntity<String> createManifest(@Valid @RequestBody ManifestDto manifestDto, Principal principal,@PathVariable Long productionId) {
        try {
            return ResponseEntity.ok(manifestService.createManifest(principal, manifestDto, productionId));
        } catch (Exception e) {
        return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{manifestId}")
    public ResponseEntity<String> updateManifest(@PathVariable Long manifestId,@Valid @RequestBody ManifestDto manifestDto, Principal principal) {
        try {
            return ResponseEntity.ok(manifestService.updateManifest(manifestId, manifestDto, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{manifestId}")
    public ResponseEntity<String> deleteManifest(@PathVariable Long manifestId) {
        try {
            return ResponseEntity.ok(manifestService.deleteManifest(manifestId));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{manifestId}")
    public ResponseEntity<Manifest> getManifest(@PathVariable Long manifestId) {
        try {
            return manifestService.getManifest(manifestId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/production/{productionId}")
    public ResponseEntity<List<Manifest>> getAllManifests(Principal principal,@PathVariable Long productionId) {
        try {
            return manifestService.getAllManifests(principal, productionId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
