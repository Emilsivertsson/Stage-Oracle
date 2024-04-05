package org.codeforpizza.registrationservice.service;

import io.restassured.RestAssured;
import org.codeforpizza.registationservice.models.*;
import org.codeforpizza.registationservice.repository.MeasurementsRepository;
import org.codeforpizza.registationservice.repository.PerformerRepository;
import org.codeforpizza.registationservice.repository.RoleRepository;
import org.codeforpizza.registationservice.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

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

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void shouldGetOneUser() {
        ApplicationUser expected = userRepository.findById(1L).get();

        assertEquals(expected.getUsername(), userService.getOneUser(1L).getBody().get().getUsername());
    }

    @Test
    @Order(2)
    void shouldNotGetOneUser() {
        assertEquals(HttpStatusCode.valueOf(204), userService.getOneUser(100L).getStatusCode());
    }

    @Test
    @Order(3)
    void shouldGetAllUsers() {
        List<ApplicationUser> expected = userRepository.findAll();
        List<ApplicationUser> actual = userService.getAllUsers().getBody();

        assertEquals(expected.get(0).getUsername(), actual.get(0).getUsername());
    }

    @Test
    @Order(8)
    void shouldNotGetAllUsers() {
        userRepository.deleteAll();
        assertEquals(HttpStatusCode.valueOf(204), userService.getAllUsers().getStatusCode());
    }

    @Test
    @Order(7)
    void shouldDeleteUser() {
        ApplicationUser expected = new ApplicationUser("username", "password", null);
        userRepository.save(expected);
        assertEquals(HttpStatusCode.valueOf(200), userService.deleteUser(2L).getStatusCode());

    }
    @Test
    @Order(4)
    void shouldNotDeleteUser() {
        assertEquals(HttpStatusCode.valueOf(204), userService.deleteUser(100L).getStatusCode());
    }

    @Test
    @Order(4)
    void shouldNotDeleteAdmin() {
        assertEquals(HttpStatusCode.valueOf(400), userService.deleteUser(1L).getStatusCode());
    }

    @Test
    @Order(5)
    void shouldUpdateUser() {
        ApplicationUser expected = userRepository.findById(2L).get();
        expected.setUsername("newUsername");
        RegistationAndUpdateDTO update = new RegistationAndUpdateDTO();
        update.setUsername(expected.getUsername());
        userService.updateUser(2L, update);
        ApplicationUser actual = userRepository.findById(2L).get();
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    @Test
    @Order(6)
    void shouldNotUpdateUser() {
        ApplicationUser expected = new ApplicationUser();
        expected.setUsername("newUsername");
        RegistationAndUpdateDTO update = new RegistationAndUpdateDTO();
        update.setUsername(expected.getUsername());
        assertEquals(HttpStatusCode.valueOf(400), userService.updateUser(100L, update).getStatusCode());

    }
}