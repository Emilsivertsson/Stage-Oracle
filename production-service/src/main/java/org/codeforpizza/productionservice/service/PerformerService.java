package org.codeforpizza.productionservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.modell.ApplicationUser;
import org.codeforpizza.productionservice.repository.PerformerRepository;
import org.codeforpizza.productionservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PerformerService {

    private final PerformerRepository performerRepository;

    private final UserRepository userRepository;

    private ApplicationUser user;
}
