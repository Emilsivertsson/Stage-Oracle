package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.Cast;
import org.codeforpizza.productionservice.modell.CastDto;
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

    @PostMapping("")
    public ResponseEntity<String> createCast(@Valid @RequestBody CastDto castDto, Principal principal,Long ManifestId) {
        try {
            return ResponseEntity.ok(castService.createCast(castDto, principal, ManifestId));
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
    public ResponseEntity<Cast> getCast(@PathVariable Long id, Principal principal, Long ManifestId) {
        try {
            return castService.getCast(id, principal, ManifestId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Cast>> getAllCasts(Principal principal, Long ManifestId) {
        try {
            return castService.getAllCasts(principal, ManifestId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

}
