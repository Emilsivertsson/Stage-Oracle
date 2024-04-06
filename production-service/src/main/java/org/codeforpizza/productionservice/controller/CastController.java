package org.codeforpizza.productionservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.service.CastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/casts")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CastController {

    private final CastService castService;

    @PostMapping("")
    public ResponseEntity<String> createCast() {
        try {
            return ResponseEntity.ok(castService.createCast());
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
