package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.ApplicationUser;
import org.codeforpizza.productionservice.modell.entitys.Manifest;
import org.codeforpizza.productionservice.modell.DTOs.ManifestDto;
import org.codeforpizza.productionservice.modell.entitys.Production;
import org.codeforpizza.productionservice.repository.ManifestRepository;
import org.codeforpizza.productionservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ManifestService {

    private final ManifestRepository manifestRepository;

    private final UserRepository userRepository;

    private ApplicationUser user;

    private Optional<Production> production;

    private Manifest manifest;


    public String createManifest(Principal principal, ManifestDto manifestDto, Long productionId) {
        try {
            user = userRepository.findByUsername(principal.getName());
            production = user.getProductions().stream().filter(p -> p.getId().equals(productionId)).findFirst();
            if (production.isPresent()) {
                manifest = new Manifest();
                manifest.setProduction(production.get());
                manifest.setTitle(manifestDto.getTitle());
                manifest.setYear(manifestDto.getYear());
                manifestRepository.save(manifest);
                return "Manifest created";
            } else {
                return "Production not found";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Error creating manifest";
        }
    }

    public String updateManifest(Long id, ManifestDto manifestDto, Principal principal) {
        try {
            user = userRepository.findByUsername(principal.getName());
            manifest = manifestRepository.findById(id).orElse(null);
            manifest.setTitle(manifestDto.getTitle());
            manifest.setYear(manifestDto.getYear());
            manifestRepository.save(manifest);
            return "Manifest updated";
        } catch (Exception e) {
            log.info(e.getMessage());
            return "Error updating manifest";
        }
    }

    public String deleteManifest(Long id) {
        try {
            manifest = manifestRepository.findById(id).orElse(null);
            manifestRepository.delete(manifest);
            return "Manifest deleted";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Error deleting manifest";
        }
    }

    public ResponseEntity<Manifest> getManifest(Long id) {
        try {
            manifest = manifestRepository.findById(id).orElse(null);
            return ResponseEntity.ok(manifest);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<List<Manifest>> getAllManifests(Principal principal, Long productionId) {
        try {
            user = userRepository.findByUsername(principal.getName());
            production = user.getProductions().stream().filter(p -> p.getId().equals(productionId)).findFirst();
            if (production.isPresent()) {
                List<Manifest> manifests = manifestRepository.findAllByProductionId(productionId);
                return ResponseEntity.ok(manifests);
            } else {
                return ResponseEntity.status(400).build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }
}
