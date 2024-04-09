package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.codeforpizza.productionservice.modell.DTOs.ApplicationUser;
import org.codeforpizza.productionservice.modell.DTOs.GetPerformerRequestDTO;
import org.codeforpizza.productionservice.modell.DTOs.PerformerDto;
import org.codeforpizza.productionservice.modell.DTOs.PerformerResponsDTO;
import org.codeforpizza.productionservice.modell.entitys.Cast;
import org.codeforpizza.productionservice.modell.entitys.Performer;
import org.codeforpizza.productionservice.repository.CastRepository;
import org.codeforpizza.productionservice.repository.PerformerRepository;
import org.codeforpizza.productionservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PerformerService {

    private final PerformerRepository performerRepository;

    private final UserRepository userRepository;

    private final CastRepository castRepository;

    private ApplicationUser user;

    private Performer performer;

    private Cast cast;

    private final HttpService httpService;

    public String updatePerformer(Long id, PerformerDto performerDto, Principal principal) {
        try {
            performer = performerRepository.findById(id).orElse(null);
            if (performer != null) {
               performer.setFirstName(performerDto.getFirstName());
                performer.setLastName(performerDto.getLastName());
                performerRepository.save(performer);
                log.info("Performer updated successfully");
                return "Performer updated successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Performer update failed";
    }

    public String deletePerformer(Long id, Principal principal) {
        try {
            performer = performerRepository.findById(id).orElse(null);
            if (performer != null) {
                performerRepository.delete(performer);
                log.info("Performer deleted successfully");
                return "Performer deleted successfully";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Performer deletion failed";
    }

    public ResponseEntity<Performer> getPerformer(Long id, Principal principal) {
        try {
            performer = performerRepository.findById(id).orElse(null);
            if (performer != null) {
                log.info("Performer retrieved successfully");
                return ResponseEntity.ok(performer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(400).build();
    }


    public ResponseEntity<List<Performer>> getAllPerformers(Principal principal, Long castId) {
        try {
            cast = castRepository.findById(castId).orElse(null);
            if (cast != null) {
                log.info("Performers retrieved successfully");
                return ResponseEntity.ok(cast.getPerformers());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(400).build();
    }

    public ResponseEntity<String> createPerformer(GetPerformerRequestDTO getPerformerRequestDTO, Principal principal, Long castId) throws IOException, ParseException {
        try {
            PerformerResponsDTO performerRespons =  httpService.getPerformer(getPerformerRequestDTO);
            log.info("performerRespons in service:" + performerRespons);
            cast = castRepository.findById(castId).orElse(null);
            performer = new Performer();
            performer.setFirstName(performerRespons.getFirstName());
            performer.setLastName(performerRespons.getLastName());
            performer.setCast(cast);
            performerRepository.save(performer);
            return ResponseEntity.ok("Performer created successfully");
        } catch (Exception e) {
            log.info("Error creating performer: " + e);
            throw new RuntimeException(e);

        }
    }
}
