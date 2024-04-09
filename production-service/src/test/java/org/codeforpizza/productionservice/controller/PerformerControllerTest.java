package org.codeforpizza.productionservice.controller;

import io.restassured.RestAssured;
import org.apache.hc.core5.http.ParseException;
import org.codeforpizza.productionservice.modell.DTOs.GetPerformerRequestDTO;
import org.codeforpizza.productionservice.modell.DTOs.MeasurementsDTO;
import org.codeforpizza.productionservice.modell.DTOs.PerformerResponsDTO;
import org.codeforpizza.productionservice.repository.UserRepository;
import org.codeforpizza.productionservice.service.HttpService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerformerControllerTest {

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

    @MockBean
    private HttpService httpService;

    @Autowired
    private UserRepository userRepository;

    private String token;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;

        token = given()
                .contentType("application/json")
                .body("{\"username\": \"user\", \"password\": \"password1\"}")
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("jwt");
    }

    @Test
    @Order(1)
    void createPerformer() throws IOException, ParseException {
        MeasurementsDTO measurementsDTO = new MeasurementsDTO(178,42,50,50,60);
        PerformerResponsDTO performerResponsDTO = new PerformerResponsDTO(2L, "John", "Doe", "Email", "Phone", "Dancer",measurementsDTO);
        when(httpService.getPerformer(any(GetPerformerRequestDTO.class))).thenReturn(performerResponsDTO);

        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body("""
                        {
  	                      "performerId": 2,
                          "username": "user"
                        }
                        """)
                .when()
                .post("/performers/2")
                .then()
                .statusCode(200);
    }

    // Not Used
    @Test
    @Order(2)
    void updatePerformer() {
    }

    @Test
    @Order(5)
    void deletePerformer() {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/performers/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    void getPerformer() {
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .get("/performers/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    void getAllPerformers() {
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .get("/performers/cast/2")
                .then()
                .statusCode(200);
    }
}