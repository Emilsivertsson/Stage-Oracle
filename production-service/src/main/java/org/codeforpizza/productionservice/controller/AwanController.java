package org.codeforpizza.productionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.DTOs.QuestionDTO;
import org.codeforpizza.productionservice.modell.entitys.AwanChats;
import org.codeforpizza.productionservice.service.AwanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/*
    * This class is responsible for handling the requests related to the Awan chatbot.

 */

@RestController
@RequestMapping("/production-api/awan")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class AwanController {

    private final AwanService awanService;

    @PostMapping("/question")
    public ResponseEntity<String> createQuestion(@Valid @RequestBody QuestionDTO questionDto, Principal principal ) {
        try {
            log.info("Creating question in api " + questionDto.question());

            return awanService.createQuestion(questionDto.question(), principal);
        } catch (Exception e) {
            log.error("Error creating question in api");
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/questions")
    public ResponseEntity<List<AwanChats>> getAllQuestions(Principal principal) {
        try {
            log.info("Getting all questions in api");
            return awanService.getAllQuestions(principal);
        } catch (Exception e) {
            log.error("Error getting all questions in api");
            return ResponseEntity.status(400).build();
        }
    }
}
