package org.codeforpizza.registrationservice.service;

import io.restassured.RestAssured;
import org.codeforpizza.registrationservice.models.entitys.ApplicationUser;
import org.codeforpizza.registrationservice.models.DTOs.LoginResponseDTO;
import org.codeforpizza.registrationservice.repository.MeasurementsRepository;
import org.codeforpizza.registrationservice.repository.PerformerRepository;
import org.codeforpizza.registrationservice.repository.RoleRepository;
import org.codeforpizza.registrationservice.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationServiceTest {

    @LocalServerPort
    private int port;


    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.26");

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PerformerRepository performerRepository;

    @Autowired
    private MeasurementsRepository measurementsRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    @Order(1)
    void registerUser() {
        String username = "username";
        String password = "password34";
        authenticationService.registerUser(username, password);
        ApplicationUser user = userRepository.findByUsername(username);

        assertEquals(username, user.getUsername());

    }

    @Test
    @Order(2)
    void loginUser() {
        String username = "username";
        String password = "password34";
        authenticationService.registerUser(username, password);
        ApplicationUser user = userRepository.findByUsername(username);

        assertEquals(username, user.getUsername());

        ResponseEntity<LoginResponseDTO> loggedInUser = authenticationService.loginUser(username, password);

        assertEquals(200, loggedInUser.getStatusCode().value());
    }
}