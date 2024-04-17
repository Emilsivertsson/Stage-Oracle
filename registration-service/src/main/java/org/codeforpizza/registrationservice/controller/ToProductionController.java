package org.codeforpizza.registrationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.codeforpizza.registrationservice.models.DTOs.GetPerformerRequestDTO;
import org.codeforpizza.registrationservice.models.entitys.Performer;
import org.codeforpizza.registrationservice.service.AuthenticationService;
import org.codeforpizza.registrationservice.service.PerformerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for getting performers to the production service
 it doesn't use authentication so thats why its CORS is set to only localhost:8081
 */

@RestController
@RequestMapping("/toProduction")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8081")
@Slf4j
public class ToProductionController {

    private final PerformerService performerService;

    private final AuthenticationService authenticationService;

    @GetMapping("")
    public ResponseEntity<Performer> getPerformerToProduction(@RequestBody GetPerformerRequestDTO getPerformerRequestDTO) {
        try {
            log.info("recived request to get performer to production");
            return performerService.getPerformerToProduction(getPerformerRequestDTO.getPerformerId());
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Performer>> getAllPerformersToProduction(@RequestBody GetPerformerRequestDTO getPerformerRequestDTO) {
        try {
            log.info("received request to get all performers to production");
            return performerService.getAllPerformersToProduction();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

}
