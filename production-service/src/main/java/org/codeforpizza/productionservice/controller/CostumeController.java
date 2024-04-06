package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.CostumeDto;
import org.codeforpizza.productionservice.service.CostumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/costumes")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CostumeController {

    private final CostumeService costumeService;


    @PostMapping("")
    public ResponseEntity<String> createCostume(@Valid @RequestBody CostumeDto costumeDto, Principal principal) {
        try {
            return ResponseEntity.ok(costumeService.createCostume(costumeDto, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCostume(@PathVariable Long id, @Valid @RequestBody CostumeDto costumeDto, Principal principal) {
        try {
            return ResponseEntity.ok(costumeService.updateCostume(id, costumeDto, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCostume(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(costumeService.deleteCostume(id,principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCostume(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(costumeService.getCostume(id, principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getAllCostumes(Principal principal) {
        try {
            return ResponseEntity.ok(costumeService.getAllCostumes(principal));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
