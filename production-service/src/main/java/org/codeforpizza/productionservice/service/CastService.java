package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.DTOs.ApplicationUser;
import org.codeforpizza.productionservice.modell.entitys.Cast;
import org.codeforpizza.productionservice.modell.DTOs.CastDto;
import org.codeforpizza.productionservice.modell.entitys.Manifest;
import org.codeforpizza.productionservice.repository.CastRepository;
import org.codeforpizza.productionservice.repository.ManifestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CastService {

    private final CastRepository castRepository;

    private final ManifestRepository manifestRepository;

    private ApplicationUser user;

    private Manifest manifest;

    private Cast cast;

    public ResponseEntity<String> createCast(CastDto castDto, Principal principal, Long manifestId) {
        try {
            log.info("getting manifest by id");
            manifest = manifestRepository.findById(manifestId).orElse(null);
            if (manifest != null) {
                log.info("creating cast");
                cast = new Cast();
                cast.setName(castDto.getName());
                cast.setManifest(manifest);
                castRepository.save(cast);
                log.info("Cast created successfully");
                return ResponseEntity.ok("Cast created successfully");
            }
        } catch (Exception e) {
            log.error("Error creating cast");
            throw new RuntimeException(e);
        }
        log.error("Error creating cast in service");
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<String> updateCast(Long id, Principal principal, CastDto castDto) {
        try {
            cast = castRepository.findById(id).orElse(null);
            if (cast != null) {
                cast.setName(castDto.getName());
                castRepository.save(cast);
                return ResponseEntity.ok("Cast updated successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<String> deleteCast(Long id, Principal principal) {
        try {
            cast = castRepository.findById(id).orElse(null);
            if (cast != null) {
                castRepository.delete(cast);
                return ResponseEntity.ok("Cast deleted successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Cast> getCast(Long id, Principal principal) {
        try {
            cast = castRepository.findById(id).orElse(null);
            if (cast != null) {
                return ResponseEntity.ok(cast);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<Cast>> getAllCasts(Principal principal, Long manifestId) {
        try {
            manifest = manifestRepository.findById(manifestId).orElse(null);
            if (manifest != null) {
                return ResponseEntity.ok(manifest.getCasts());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.notFound().build();
    }
}
