package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.ApplicationUser;
import org.codeforpizza.productionservice.modell.Costume;
import org.codeforpizza.productionservice.modell.Garment;
import org.codeforpizza.productionservice.modell.GarmentDto;
import org.codeforpizza.productionservice.repository.CostumeRepository;
import org.codeforpizza.productionservice.repository.GarmentRepository;
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
public class GarmentService {

    private final GarmentRepository garmentRepository;

    private final UserRepository userRepository;

    private final CostumeRepository costumeRepository;

    private ApplicationUser user;

    private Garment garment;

    private Costume costume;



    public ResponseEntity<String> createGarment(GarmentDto garmentDTO, Principal principal, Long costumeId) {
        try {
            costume = costumeRepository.findById(costumeId).orElse(null);
            if (costume != null) {
                garment = new Garment();
                garment.setName(garmentDTO.getName());
                garment.setDescription(garmentDTO.getDescription());
                garment.setCostume(costume);
                garmentRepository.save(garment);
                return ResponseEntity.ok("Garment created successfully");
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> updateGarment(Long id, Principal principal, GarmentDto garmentDto) {
        try {
            garment = garmentRepository.findById(id).orElse(null);
            if (garment != null) {
                garment.setName(garmentDto.getName());
                garment.setDescription(garmentDto.getDescription());
                garmentRepository.save(garment);
                return ResponseEntity.ok("Garment updated successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<String> deleteGarment(Long id, Principal principal) {
        try {
            garment = garmentRepository.findById(id).orElse(null);
            if (garment != null) {
                garmentRepository.delete(garment);
                return ResponseEntity.ok("Garment deleted successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Garment> getGarment(Long id, Principal principal) {
        try {
            garment = garmentRepository.findById(id).orElse(null);
            if (garment != null) {
                return ResponseEntity.ok(garment);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<Garment>> getAllGarments(Principal principal, Long costumeId) {
        try {
            costume = costumeRepository.findById(costumeId).orElse(null);
            if (costume != null) {
                return ResponseEntity.ok(costume.getGarments());
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
