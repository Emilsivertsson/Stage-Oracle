package org.codeforpizza.productionservice.controller;

import io.restassured.RestAssured;
import org.codeforpizza.productionservice.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CostumeControllerTest {

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
    void createCostume() {
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"name\": \"Create Costume test\"}")
                .when()
                .post("/costumes/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void updateCostume() {
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"name\": \"Update Costume test\"}")
                .when()
                .put("/costumes/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    void deleteCostume() {
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/costumes/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    void getCostume() {
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .get("/costumes/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Update Costume test"));
    }

    @Test
    @Order(4)
    void getAllCostumes() {
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .get("/costumes/act/2")
                .then()
                .statusCode(200);

    }
}