package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.DTOs.AwanRespons;
import org.codeforpizza.productionservice.modell.entitys.ApplicationUser;
import org.codeforpizza.productionservice.modell.entitys.AwanChats;
import org.codeforpizza.productionservice.repository.AwanRespository;
import org.codeforpizza.productionservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

/*
    * This class is responsible for handling the requests related to the Awan chatbot.
    * it forwards the requests to the HttpService class and saves the response in the database.
    * in order to show the user the history of the questions asked.
 */

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AwanService {

    private final HttpService httpService;

    private final AwanRespository awanChatsRepository;

    private final UserRepository userRepository;

    private AwanChats awanChats;

    public ResponseEntity<String> createQuestion(String question, Principal principal) {
        try {
            log.info("Creating question in service");
            ApplicationUser user = userRepository.findByUsername(principal.getName());
            String response = httpService.createQuestion(question);

            awanChats = new AwanChats();
            awanChats.setQuestion(question);
            awanChats.setAnswer(response);
            awanChats.setApplicationUser(user);
            awanChatsRepository.save(awanChats);
            user.getAwanChatses().add(awanChats);
            return ResponseEntity.ok("Question created");

        } catch (Exception e) {
            log.error("Error creating question in service");
            return null;
        }
    }

    public ResponseEntity<List<AwanChats>> getAllQuestions(Principal principal) {
        try {
            log.info("Getting all questions in service");
            ApplicationUser user = userRepository.findByUsername(principal.getName());
            return ResponseEntity.ok(user.getAwanChatses());

        } catch (Exception e) {
            log.error("Error getting all questions in service");
            return null;
        }
    }


}
