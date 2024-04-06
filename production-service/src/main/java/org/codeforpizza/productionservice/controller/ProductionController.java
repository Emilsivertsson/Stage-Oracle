package org.codeforpizza.productionservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.Production;
import org.codeforpizza.productionservice.service.ProductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productions")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ProductionController {

    private final ProductionService productionService;

    @PostMapping("")
    public ResponseEntity<String> createProduction() {
        try {
            productionService.createProduction();
            log.info("Creating production");
            return ResponseEntity.ok("Production created");
        } catch (Exception e) {
            log.error("Error creating production");
            return ResponseEntity.badRequest().body("Error creating production");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduction(@PathVariable Long id) {
        try {
            productionService.deleteProduction(id);
            log.info("Deleting production");
            return ResponseEntity.status(205).body("Production deleted");
        } catch (Exception e) {
            log.error("Error deleting production");
            return ResponseEntity.badRequest().body("Error deleting production");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduction(@PathVariable Long id) {
        try {
            productionService.updateProduction(id);
            log.info("Updating production");
            return ResponseEntity.ok("Production updated");
        } catch (Exception e) {
            log.error("Error updating production");
            return ResponseEntity.badRequest().body("Error updating production");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Production> getProduction(@PathVariable Long id) {
        try {
            log.info("Getting production");
            return ResponseEntity.ok(productionService.getProduction(id));
        } catch (Exception e) {
            log.error("Error getting production");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Production>> getAllProductions() {
        try {
            log.info("Getting all productions");
            return ResponseEntity.ok(productionService.getAllProductions());
        } catch (Exception e) {
            log.error("Error getting all productions");
            return ResponseEntity.badRequest().build();
        }
    }


}
