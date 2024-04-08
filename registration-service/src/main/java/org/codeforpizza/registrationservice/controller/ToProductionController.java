package org.codeforpizza.registrationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.registrationservice.models.GetPerformerRequestDTO;
import org.codeforpizza.registrationservice.models.Performer;
import org.codeforpizza.registrationservice.service.AuthenticationService;
import org.codeforpizza.registrationservice.service.PerformerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/toProduction")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class ToProductionController {

    private final PerformerService performerService;

    private final AuthenticationService authenticationService;

    @GetMapping("")
    public ResponseEntity<Performer> getPerformerToProduction(@RequestBody GetPerformerRequestDTO getPerformerRequestDTO) {
        try {
            log.info("recived request to get performer to production");
            Boolean isAuthenticated = authenticationService.isAuthenticated(getPerformerRequestDTO.getUsername());
            log.info("isAuthenticated: " + isAuthenticated);
            if(isAuthenticated.equals(true)){
                log.info("Getting performer by username");
                return performerService.getPerformerToProduction(getPerformerRequestDTO.getPerformerId());
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}