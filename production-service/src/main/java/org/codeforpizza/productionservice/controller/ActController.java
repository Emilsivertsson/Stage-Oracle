package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.Act;
import org.codeforpizza.productionservice.modell.ActDto;
import org.codeforpizza.productionservice.service.ActService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/acts")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ActController {

    private final ActService actService;

    @PostMapping("/{performerId}")
    public ResponseEntity<String> createAct(@Valid @RequestBody ActDto act, Principal principal,@PathVariable Long performerId) {
        try {
            log.info("Creating act");
            return ResponseEntity.ok(actService.createAct(act, principal,performerId));
        } catch (Exception e) {
            log.error("Error creating act", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{actId}")
    public ResponseEntity<String> updateAct(@PathVariable Long actId, Principal principal, @Valid @RequestBody ActDto actDto) {
        try {
            log.info("Updating act");
            return ResponseEntity.ok(actService.updateAct(actId, principal, actDto));
        } catch (Exception e) {
            log.error("Error updating act", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{actId}")
    public ResponseEntity<String> deleteAct(@PathVariable Long actId, Principal principal) {
        try {
            log.info("Deleting act");
            return actService.deleteAct(actId, principal);
        } catch (Exception e) {
            log.error("Error deleting act", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{actId}")
    public ResponseEntity<Act> getAct(@PathVariable Long actId, Principal principal) {
        try {
            log.info("Getting act");
            return actService.getAct(actId, principal);
        } catch (Exception e) {
            log.error("Error getting act", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/performer/{performerId}")
    public ResponseEntity<List<Act>> getAllActs(Principal principal,@PathVariable Long performerId) {
        try {
            log.info("Getting acts");
            return actService.getActs(principal,performerId);
        } catch (Exception e) {
            log.error("Error getting acts", e);
            return ResponseEntity.badRequest().build();
        }
    }


}
