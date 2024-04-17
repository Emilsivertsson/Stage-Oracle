package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.ApplicationUser;
import org.codeforpizza.productionservice.modell.entitys.Production;
import org.codeforpizza.productionservice.modell.DTOs.ProductionDto;
import org.codeforpizza.productionservice.repository.ProductionRepository;
import org.codeforpizza.productionservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ProductionService {

    private final ProductionRepository productionRepository;

    private final UserRepository userRepository;

    private ApplicationUser user;

    public ResponseEntity<String> createProduction(ProductionDto productionDto, Principal principal) {
        try {
            user = userRepository.findByUsername(principal.getName());
            Production production = new Production();
            production.setTitle(productionDto.getTitle());
            production.setYear(productionDto.getYear());
            production.setDescription(productionDto.getDescription());
            production.setInRotation(productionDto.getInRotation());
            production.setApplicationUser(user);
            productionRepository.save(production);
            user.getProductions().add(production);
            log.info("Creating production");
            return ResponseEntity.ok("Production created");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error creating production");
        }

    }

    public ResponseEntity<String> deleteProduction(Long id, Principal principal) {
        try {
            userRepository.findByUsername(principal.getName())
                    .getProductions()
                    .remove(productionRepository
                            .findById(id)
                            .get());
            productionRepository.deleteById(id);
            log.info("Deleting production");
            return ResponseEntity.ok("Production deleted");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error deleting production");
        }
    }

    public ResponseEntity<Production> getProduction(Long id, Principal principal) {
        try {
            user = userRepository.findByUsername(principal.getName());
            return ResponseEntity.ok(user.getProductions()
                    .stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElse(null));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Production>> getAllProductions(Principal principal) {
        try {
            user = userRepository.findByUsername(principal.getName());
            return ResponseEntity.ok(user.getProductions());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> updateProduction(Long id, ProductionDto productionDto, Principal principal) {
        try {
            user = userRepository.findByUsername(principal.getName());
            Production production = user.getProductions()
                    .stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (production != null) {
                production.setTitle(productionDto.getTitle());
                production.setYear(productionDto.getYear());
                production.setDescription(productionDto.getDescription());
                production.setInRotation(productionDto.getInRotation());
                productionRepository.save(production);
                log.info("Updating production");
                return ResponseEntity.ok("Production updated");
            } else {
                log.error("Error updating production");
                return ResponseEntity.badRequest().body("Error updating production");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error updating production");
        }
    }
}
