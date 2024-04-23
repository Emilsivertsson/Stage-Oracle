package org.codeforpizza.productionservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.DTOs.Email;
import org.codeforpizza.productionservice.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is the controller for the email
 * It is responsible for handling the requests sending emails to performers
 */

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody Email email) {
        try{
            log.info("received request to send email");
            return emailService.sendEmail(email);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
