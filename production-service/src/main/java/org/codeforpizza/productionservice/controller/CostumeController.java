package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.Costume;
import org.codeforpizza.productionservice.modell.DTOs.CostumeDto;
import org.codeforpizza.productionservice.service.CostumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/costumes")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CostumeController {

    private final CostumeService costumeService;

    @PostMapping("/{actId}")
    public ResponseEntity<String> createCostume(@Valid @RequestBody CostumeDto costumeDto,@PathVariable Long actId) {
        try {
            return costumeService.createCostume(costumeDto, actId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{costumeId}")
    public ResponseEntity<String> updateCostume(@PathVariable Long costumeId, @Valid @RequestBody CostumeDto costumeDto) {
        try {
            return costumeService.updateCostume(costumeId, costumeDto);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{costumeId}")
    public ResponseEntity<String> deleteCostume(@PathVariable Long costumeId) {
        try {
            return costumeService.deleteCostume(costumeId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{costumeId}")
    public ResponseEntity<Costume> getCostume(@PathVariable Long costumeId) {
        try {
            return costumeService.getCostume(costumeId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/act/{actId}")
    public ResponseEntity<List<Costume>> getAllCostumes(@PathVariable Long actId) {
        try {
            return costumeService.getAllCostumes(actId);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
