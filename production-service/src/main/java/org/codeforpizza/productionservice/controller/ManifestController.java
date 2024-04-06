package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.ManifestDto;
import org.codeforpizza.productionservice.service.ManifestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/manifests")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ManifestController {

    private final ManifestService manifestService;

    @PostMapping("")
    public ResponseEntity<String> createManifest(Principal principal,@Valid @RequestBody ManifestDto manifestDto) {
        try {
            return ResponseEntity.ok(manifestService.createManifest(principal, manifestDto));
        } catch (Exception e) {
        return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateManifest(@PathVariable Long id,@Valid @RequestBody ManifestDto manifestDto, Principal principal) {
        try {
            return ResponseEntity.ok(manifestService.updateManifest(id, manifestDto, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManifest(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(manifestService.deleteManifest(id, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getManifest(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(manifestService.getManifest(id, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getAllManifests(Principal principal) {
        try {
            return ResponseEntity.ok(manifestService.getAllManifests(principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
