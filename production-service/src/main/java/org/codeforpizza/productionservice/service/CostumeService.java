package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.Act;
import org.codeforpizza.productionservice.modell.entitys.ApplicationUser;
import org.codeforpizza.productionservice.modell.entitys.Costume;
import org.codeforpizza.productionservice.modell.DTOs.CostumeDto;
import org.codeforpizza.productionservice.repository.ActRepository;
import org.codeforpizza.productionservice.repository.CostumeRepository;
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
public class CostumeService {

    private final CostumeRepository costumeRepository;

    private final ActRepository actRepository;

    private Act act;

    private Costume costume;

    public ResponseEntity<String> createCostume(CostumeDto costumeDto, Long actId) {
        try {
            act = actRepository.findById(actId).orElse(null);
            if (act != null) {
                costume = new Costume();
                costume.setName(costumeDto.getName());
                costume.setAct(act);
                costumeRepository.save(costume);
                return ResponseEntity.ok("Costume created successfully");
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> updateCostume(Long id, CostumeDto costumeDto) {
        try {
            costume = costumeRepository.findById(id).orElse(null);
            if (costume != null) {
                costume.setName(costumeDto.getName());
                costumeRepository.save(costume);
                return ResponseEntity.ok("Costume updated successfully");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<String> deleteCostume(Long id) {
        try {
            costume = costumeRepository.findById(id).orElse(null);
            if (costume != null) {
                costumeRepository.delete(costume);
                return ResponseEntity.ok("Costume deleted successfully");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Costume> getCostume(Long id) {
        try {
            costume = costumeRepository.findById(id).orElse(null);
            if (costume != null) {
                return ResponseEntity.ok(costume);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<Costume>> getAllCostumes( Long actId) {
        try {
            act = actRepository.findById(actId).orElse(null);
            if (act != null) {
                return ResponseEntity.ok(act.getCostumes());
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
