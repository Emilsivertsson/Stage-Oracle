package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.Act;
import org.codeforpizza.productionservice.modell.ActDto;
import org.codeforpizza.productionservice.modell.ApplicationUser;
import org.codeforpizza.productionservice.modell.Performer;
import org.codeforpizza.productionservice.repository.ActRepository;
import org.codeforpizza.productionservice.repository.PerformerRepository;
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
public class ActService {

    private final ActRepository actRepository;

    private final UserRepository userRepository;

    private final PerformerRepository performerRepository;

    private ApplicationUser user;

    private Act act;

    private Performer performer;

    public String createAct(ActDto actDTO, Principal principal, Long performerId) {
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
            throw new RuntimeException(e);
        }
    }

    public String updateAct(Long id, Principal principal, ActDto actDto) {
        try {
            act = actRepository.findById(id).orElse(null);
            if (act != null) {
                act.setTitle(actDto.getTitle());
                actRepository.save(act);
                return "Act updated successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Act update failed";
    }

    public ResponseEntity<String> deleteAct(Long id, Principal principal) {
        try {
            act = actRepository.findById(id).orElse(null);
            if (act != null) {
                actRepository.delete(act);
                return ResponseEntity.ok("Act deleted successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Act> getAct(Long id, Principal principal) {
        try {
            act = actRepository.findById(id).orElse(null);
            if (act != null) {
                return ResponseEntity.ok(act);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<List<Act>> getActs(Principal principal, Long performerId) {
        try {
            performer = performerRepository.findById(performerId).orElse(null);
            if (performer != null) {
                return ResponseEntity.ok(performer.getActs());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
    }
        return ResponseEntity.notFound().build();
    }
}
