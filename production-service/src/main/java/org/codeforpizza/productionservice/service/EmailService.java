package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import org.codeforpizza.productionservice.modell.DTOs.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${email.from}")
    private String fromAdress;

    private final JavaMailSender javaMailSender;

    public ResponseEntity<String> sendEmail(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAdress);
        simpleMailMessage.setTo(email.adress());
        simpleMailMessage.setSubject(email.subject());
        simpleMailMessage.setText(email.body() + "\n This is sent from Stage Oracle Application, You cant answer this email. " );
        javaMailSender.send(simpleMailMessage);
        return ResponseEntity.ok("Email sent");
    }
}
