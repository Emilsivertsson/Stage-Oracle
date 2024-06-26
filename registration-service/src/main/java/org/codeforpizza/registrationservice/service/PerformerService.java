package org.codeforpizza.registrationservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.registrationservice.models.entitys.ApplicationUser;
import org.codeforpizza.registrationservice.models.DTOs.MeasurementsDTO;
import org.codeforpizza.registrationservice.models.entitys.Performer;
import org.codeforpizza.registrationservice.models.DTOs.PerformerDTO;
import org.codeforpizza.registrationservice.repository.PerformerRepository;
import org.codeforpizza.registrationservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PerformerService {

    private final PerformerRepository performerRepository;

    private final UserRepository userRepository;

    public ResponseEntity<Optional<Performer>> getPerformer(Principal principal) {
        if (userRepository.existsByUsername(principal.getName())) {
            ApplicationUser user = userRepository.findByUsername(principal.getName());
            log.info("Performer found");
            return ResponseEntity.ok(performerRepository.findById(user.getPerformer().getId()));
        } else {
            log.info("Performer not found");
            return ResponseEntity.status(404).build();
        }
    }

    public ResponseEntity<Performer> updatePerformer(Principal principal, PerformerDTO performer) {
            if (userRepository.existsByUsername(principal.getName())) {
                ApplicationUser user = userRepository.findByUsername(principal.getName());

                Optional<Performer> performerToUpdate = performerRepository.findById(user.getPerformer().getId());

                performerToUpdate.get().setFirstName(performer.getFirstName());
                performerToUpdate.get().setLastName(performer.getLastName());
                performerToUpdate.get().setEmail(performer.getEmail());
                performerToUpdate.get().setPhoneNr(performer.getPhoneNr());
                performerToUpdate.get().setEmail(performer.getEmail());
                performerToUpdate.get().setDepartment(performer.getDepartment());

                log.info("Performer updated successfully");
                return ResponseEntity.ok(performerRepository.save(performerToUpdate.get()));
            } else {
                log.info("Performer not found");
                return ResponseEntity.status(404).build();
            }
    }

    public ResponseEntity<Performer> updateMeasurements(Principal principal, MeasurementsDTO measurements) {

                if (userRepository.existsByUsername(principal.getName())) {
                    ApplicationUser user = userRepository.findByUsername(principal.getName());

                    Optional<Performer> performerToUpdate = performerRepository.findById(user.getPerformer().getId());

                    performerToUpdate.get().getMeasurements().setHead(measurements.getHead());
                    performerToUpdate.get().getMeasurements().setHeight(measurements.getHeight());
                    performerToUpdate.get().getMeasurements().setJacketSize(measurements.getJacketSize());
                    performerToUpdate.get().getMeasurements().setPantSize(measurements.getPantSize());
                    performerToUpdate.get().getMeasurements().setShoeSize(measurements.getShoeSize());
                    log.info("Measurements updated successfully");
                    return ResponseEntity.ok(performerRepository.save(performerToUpdate.get()));
                } else {
                    log.info("Performer not found");
                    return ResponseEntity.status(404).build();
                }
    }

    public ResponseEntity<String> deleteAccount(Principal principal) {
            if(userRepository.existsByUsername(principal.getName())) {
                userRepository.deleteByUsernameIgnoreCase(principal.getName());
                log.info("Performer deleted successfully");
                return ResponseEntity.ok("Performer deleted successfully");
            } else {
                log.info("Performer not found");
                return ResponseEntity.status(404).build();
            }
    }

    /**
     * This method is used to get a performer to the production service
     * @param performerId the id of the performer wanted
     * @return the performer
     */

    public ResponseEntity<Performer> getPerformerToProduction(Long performerId) {
        try {
            if (performerRepository.existsById(performerId)) {
                log.info("Performer found, returning performer");
                return ResponseEntity.ok(performerRepository.findById(performerId).get());
            } else {
                return ResponseEntity.status(404).build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }

    /**
     * This method is used to get all performers to the production service
     * @return all performers
     */

    public ResponseEntity<Iterable<Performer>> getAllPerformersToProduction() {
        try {
            List<Performer> performers = performerRepository.findAll();
            performers.removeIf(performer -> performer.getId() == 1);
            log.info("Returning all performers");
            return ResponseEntity.ok(performers);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
