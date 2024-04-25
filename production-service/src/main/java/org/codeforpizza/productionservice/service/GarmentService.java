package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.Costume;
import org.codeforpizza.productionservice.modell.entitys.Garment;
import org.codeforpizza.productionservice.modell.DTOs.GarmentDto;
import org.codeforpizza.productionservice.repository.CostumeRepository;
import org.codeforpizza.productionservice.repository.GarmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class GarmentService {

    private final GarmentRepository garmentRepository;

    private final CostumeRepository costumeRepository;

    private Garment garment;

    private Costume costume;

    public ResponseEntity<String> createGarment(GarmentDto garmentDTO, Long costumeId) {
        try {
            costume = costumeRepository.findById(costumeId).orElse(null);
            if (costume != null) {
                garment = new Garment();
                garment.setName(garmentDTO.name());
                garment.setDescription(garmentDTO.description());
                garment.setIsDone(garmentDTO.isDone());
                garment.setCostume(costume);
                garmentRepository.save(garment);
                return ResponseEntity.ok("Garment created successfully");
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> updateGarment(Long id, GarmentDto garmentDto) {
        try {
            garment = garmentRepository.findById(id).orElse(null);
            if (garment != null) {
                garment.setName(garmentDto.name());
                garment.setDescription(garmentDto.description());
                garment.setIsDone(garmentDto.isDone());
                garmentRepository.save(garment);
                return ResponseEntity.ok("Garment updated successfully");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<String> deleteGarment(Long id) {
        try {
            garment = garmentRepository.findById(id).orElse(null);
            if (garment != null) {
                garmentRepository.delete(garment);
                return ResponseEntity.ok("Garment deleted successfully");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Garment> getGarment(Long id) {
        try {
            garment = garmentRepository.findById(id).orElse(null);
            if (garment != null) {
                return ResponseEntity.ok(garment);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<Garment>> getAllGarments(Long costumeId) {
        try {
            costume = costumeRepository.findById(costumeId).orElse(null);
            if (costume != null) {
                return ResponseEntity.ok(costume.getGarments());
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> updateGarmentStatus(Long garmentId) {
        try {
            log.info("Updating garment status");
            garment = garmentRepository.findById(garmentId).orElse(null);
            if (garment != null) {
                garment.setIsDone(garment.getIsDone() ? false : true);
            }
            return ResponseEntity.ok("Garment status updated successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }
}
