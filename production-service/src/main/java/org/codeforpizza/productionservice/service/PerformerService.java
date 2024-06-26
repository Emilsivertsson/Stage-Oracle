package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.codeforpizza.productionservice.modell.DTOs.GetPerformerRequestDTO;
import org.codeforpizza.productionservice.modell.DTOs.MeasurementsDTO;
import org.codeforpizza.productionservice.modell.DTOs.PerformerDto;
import org.codeforpizza.productionservice.modell.DTOs.PerformerResponsDTO;
import org.codeforpizza.productionservice.modell.entitys.Cast;
import org.codeforpizza.productionservice.modell.entitys.Measurements;
import org.codeforpizza.productionservice.modell.entitys.Performer;
import org.codeforpizza.productionservice.repository.CastRepository;
import org.codeforpizza.productionservice.repository.MeasurementsRepository;
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

    private final MeasurementsRepository measurementsRepository;

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

    public ResponseEntity<String> createPerformer(GetPerformerRequestDTO getPerformerRequestDTO, Long castId) {
        try {
            PerformerResponsDTO performerRespons =  httpService.getPerformer(getPerformerRequestDTO);
            log.info("performerRespons in service:" + performerRespons);
            cast = castRepository.findById(castId).orElse(null);
            performer = new Performer(performerRespons.getFirstName(),
                    performerRespons.getLastName(),
                    performerRespons.getEmail(),
                    performerRespons.getPhoneNr(),
                    performerRespons.getDepartment());
            Measurements measurements = new Measurements(performerRespons.getMeasurements().getHeight(),
                    performerRespons.getMeasurements().getJacketSize(),
                    performerRespons.getMeasurements().getPantSize(),
                    performerRespons.getMeasurements().getShoeSize(),
                    performerRespons.getMeasurements().getHead());
            performer.setMeasurements(measurements);
            measurementsRepository.save(measurements);
            performer.setCast(cast);
            performerRepository.save(performer);
            return ResponseEntity.ok("Performer created successfully");
        } catch (Exception e) {
            log.info("Error creating performer: " + e);
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<PerformerResponsDTO>> getAllPerformersFromRegistry( Long castId) {
        try {
            List<Performer> performersInCast = castRepository.findById(castId).orElse(null).getPerformers();

            List<PerformerResponsDTO> performersFromRegistry = httpService.getAllPerformers();
            List<PerformerResponsDTO> performers = new ArrayList<>();
            log.info("Performers from registry: " + performersFromRegistry);

            for (PerformerResponsDTO performerFromRegister : performersFromRegistry) {
                if (performersInCast.stream().noneMatch(p -> p.getId().equals(performerFromRegister.getId()))) {
                    PerformerResponsDTO performerResponsDTO = performersFromRegistry.stream()
                            .filter(p -> p.getId().equals(performerFromRegister.getId()))
                            .findFirst()
                            .orElse(null);
                    performers.add(performerResponsDTO);
                    }
            }
            log.info("Performers created from registry: " + performers);
            log.info("Performers retrieved successfully");
            return ResponseEntity.ok(performers);
        } catch (IOException | ParseException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(400).build();
        }
    }
}
