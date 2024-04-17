package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.entitys.Act;
import org.codeforpizza.productionservice.modell.DTOs.ActDto;
import org.codeforpizza.productionservice.modell.entitys.Performer;
import org.codeforpizza.productionservice.repository.ActRepository;
import org.codeforpizza.productionservice.repository.PerformerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ActService {

    private final ActRepository actRepository;


    private final PerformerRepository performerRepository;

    private Act act;

    private Performer performer;

    public String createAct(ActDto actDTO, Long performerId) {
        try{
            performer = performerRepository.findById(performerId).orElse(null);
            if(performer != null){
                act = new Act();
                act.setTitle(actDTO.getTitle());
                act.setPerformer(performer);
                actRepository.save(act);
                return "Act created successfully";
            } else {
                return "Performer not found";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return "Act creation failed";
        }
    }

    public String updateAct(Long id, ActDto actDto) {
        try {
            act = actRepository.findById(id).orElse(null);
            if (act != null) {
                act.setTitle(actDto.getTitle());
                actRepository.save(act);
                return "Act updated successfully";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "Act update failed";
    }

    public ResponseEntity<String> deleteAct(Long id) {
        try {
            act = actRepository.findById(id).orElse(null);
            if (act != null) {
                actRepository.delete(act);
                return ResponseEntity.ok("Act deleted successfully");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Act> getAct(Long id) {
        try {
            act = actRepository.findById(id).orElse(null);
            if (act != null) {
                return ResponseEntity.ok(act);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<List<Act>> getActs( Long performerId) {
        try {
            performer = performerRepository.findById(performerId).orElse(null);
            if (performer != null) {
                return ResponseEntity.ok(performer.getActs());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
    }
        return ResponseEntity.notFound().build();
    }
}
