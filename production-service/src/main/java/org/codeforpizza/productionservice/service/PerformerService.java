package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.codeforpizza.productionservice.modell.DTOs.GetPerformerRequestDTO;
import org.codeforpizza.productionservice.modell.DTOs.PerformerDto;
import org.codeforpizza.productionservice.modell.DTOs.PerformerResponsDTO;
import org.codeforpizza.productionservice.modell.entitys.Cast;
import org.codeforpizza.productionservice.modell.entitys.Performer;
import org.codeforpizza.productionservice.repository.CastRepository;
import org.codeforpizza.productionservice.repository.PerformerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PerformerService {

    private final PerformerRepository performerRepository;

    private final CastRepository castRepository;

    private Performer performer;

    private Cast cast;

    private final HttpService httpService;

    public ResponseEntity<String> updatePerformer(Long id, PerformerDto performerDto) {
        try {
            performer = performerRepository.findById(id).orElse(null);
            if (performer != null) {
               performer.setFirstName(performerDto.firstName());
                performer.setLastName(performerDto.lastName());
                performerRepository.save(performer);
                log.info("Performer updated successfully");
                return ResponseEntity.ok("Performer updated successfully");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(400).build();
    }

    public ResponseEntity<String> deletePerformer(Long id) {
        try {
            performer = performerRepository.findById(id).orElse(null);
            if (performer != null) {
                performerRepository.delete(performer);
                log.info("Performer deleted successfully");
                return ResponseEntity.ok("Performer deleted successfully");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(400).build();
    }

    public ResponseEntity<Performer> getPerformer(Long id) {
        try {
            performer = performerRepository.findById(id).orElse(null);
            if (performer != null) {
                log.info("Performer retrieved successfully");
                return ResponseEntity.ok(performer);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(400).build();
    }


    public ResponseEntity<List<Performer>> getAllPerformers( Long castId) {
        try {
            cast = castRepository.findById(castId).orElse(null);
            if (cast != null) {
                log.info("Performers retrieved successfully");
                return ResponseEntity.ok(cast.getPerformers());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(400).build();
    }

    public ResponseEntity<String> createPerformer(GetPerformerRequestDTO getPerformerRequestDTO, Long castId) throws IOException, ParseException {
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
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Performer>> getAllPerformersFromRegistry(GetPerformerRequestDTO getPerformerRequestDTO, Long castId) {
        try {
            List<Performer> performersInCast = castRepository.findById(castId).orElse(null).getPerformers();

            List<PerformerResponsDTO> performersFromRegistry = httpService.getAllPerformers(getPerformerRequestDTO);
            List<Performer> performers = new ArrayList<>();
            log.info("Performers from registry: " + performersFromRegistry);

            for (PerformerResponsDTO performerResponsDTO : performersFromRegistry) {
                performer = new Performer();
                performer.setId(performerResponsDTO.getId());
                performer.setFirstName(performerResponsDTO.getFirstName());
                performer.setLastName(performerResponsDTO.getLastName());
                //check if a performer with the same id already exists in the cast
                if (performersInCast.stream().noneMatch(p -> p.getId().equals(performer.getId()))) {
                    performers.add(performer);
                }
            }
            log.info("Performers created from registry: " + performers);
            log.info("Performers retrieved successfully");
            return ResponseEntity.ok(performers);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
