package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.ApplicationUser;
import org.codeforpizza.productionservice.modell.Cast;
import org.codeforpizza.productionservice.modell.CastDto;
import org.codeforpizza.productionservice.modell.Manifest;
import org.codeforpizza.productionservice.repository.CastRepository;
import org.codeforpizza.productionservice.repository.ManifestRepository;
import org.codeforpizza.productionservice.repository.UserRepository;
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

    public String createCast(CastDto castDto, Principal principal, Long manifestId) {
        try {
            manifest = manifestRepository.findById(manifestId).orElse(null);
            if (manifest != null) {
                cast = new Cast();
                cast.setName(castDto.getName());
                manifest.getCasts().add(cast);
                castRepository.save(cast);
                return "Cast created successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Cast creation failed";
    }

    public String updateCast(Long id, Principal principal, CastDto castDto) {
        try {
            cast = castRepository.findById(id).orElse(null);
            if (cast != null) {
                cast.setName(castDto.getName());
                castRepository.save(cast);
                return "Cast updated successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Cast update failed";
    }

    public String deleteCast(Long id, Principal principal) {
        try {
            cast = castRepository.findById(id).orElse(null);
            if (cast != null) {
                castRepository.delete(cast);
                return "Cast deleted successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Cast deletion failed";
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
