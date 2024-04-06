package org.codeforpizza.productionservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.productionservice.service.ManifestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manifests")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ManifestController {

    private final ManifestService manifestService;
}
