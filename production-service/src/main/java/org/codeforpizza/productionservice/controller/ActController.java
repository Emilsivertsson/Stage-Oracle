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

    @PostMapping("")
    public ResponseEntity<Act> createAct(@Valid @RequestBody ActDto act, Principal principal) {
        try {
            log.info("Creating act");
            return ResponseEntity.ok(actService.createAct(act, principal));
        } catch (Exception e) {
            log.error("Error creating act", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Act> updateAct(@PathVariable Long id, Principal principal,@Valid @RequestBody ActDto actDto) {
        try {
            log.info("Updating act");
            return ResponseEntity.ok(actService.updateAct(id, principal, actDto));
        } catch (Exception e) {
            log.error("Error updating act", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAct(@PathVariable Long id, Principal principal) {
        try {
            log.info("Deleting act");
            actService.deleteAct(id, principal);
            return ResponseEntity.ok("Act deleted");
        } catch (Exception e) {
            log.error("Error deleting act", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Act> getAct(@PathVariable Long id, Principal principal) {
        try {
            log.info("Getting act");
            return ResponseEntity.ok(actService.getAct(id, principal));
        } catch (Exception e) {
            log.error("Error getting act", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Act>> getActs(Principal principal) {
        try {
            log.info("Getting acts");
            return ResponseEntity.ok(actService.getActs(principal));
        } catch (Exception e) {
            log.error("Error getting acts", e);
            return ResponseEntity.badRequest().build();
        }
    }


}
