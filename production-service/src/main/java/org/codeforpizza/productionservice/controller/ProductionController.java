package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.Production;
import org.codeforpizza.productionservice.modell.DTOs.ProductionDto;
import org.codeforpizza.productionservice.service.ProductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/productions")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ProductionController {

    private final ProductionService productionService;

    @PostMapping("")
    public ResponseEntity<String> createProduction(Principal principal,@Valid @RequestBody ProductionDto productionDto) {
        try {
            log.info("Creating production");
            return productionService.createProduction(productionDto, principal);
        } catch (Exception e) {
            log.error("Error creating production");
            return ResponseEntity.badRequest().body("Error creating production");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduction(@PathVariable Long id, Principal principal) {
        try {
            log.info("Deleting production");
            return productionService.deleteProduction(id, principal);
        } catch (Exception e) {
            log.error("Error deleting production");
            return ResponseEntity.badRequest().body("Error deleting production");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduction(@PathVariable Long id,@Valid @RequestBody ProductionDto productionDto, Principal principal) {
        try {
            log.info("Updating production");
            return productionService.updateProduction(id, productionDto, principal);
        } catch (Exception e) {
            log.error("Error updating production");
            return ResponseEntity.badRequest().body("Error updating production");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Production> getProduction(@PathVariable Long id, Principal principal) {
        try {
            log.info("Getting production");
            return productionService.getProduction(id, principal);
        } catch (Exception e) {
            log.error("Error getting production");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Production>> getAllProductions(Principal principal) {
        try {
            log.info("Getting all productions");
            return productionService.getAllProductions(principal);
        } catch (Exception e) {
            log.error("Error getting all productions");
            return ResponseEntity.badRequest().build();
        }
    }


}
