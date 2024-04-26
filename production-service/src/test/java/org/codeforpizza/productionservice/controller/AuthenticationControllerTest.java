package org.codeforpizza.productionservice.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationControllerTest {

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


    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    @Order(1)
    void registerUser() {
        given()
                .contentType("application/json")
                .body("{\"username\": \"kalle\", \"password\": \"password1\"}")
                .when()
                .post("/production-api/auth/register")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void loginUser() {
        given()
                .contentType("application/json")
                .body("{\"username\": \"kalle\", \"password\": \"password1\"}")
                .when()
                .post("/production-api/auth/login")
                .then()
                .statusCode(200);
    }

    @Test
    void isAuthenticated() {
        given()
                .contentType("application/json")
                .body("{\"username\": \"kalle\"}")
                .when()
                .get("/production-api/auth/isAuthenticated")
                .then()
                .statusCode(200);
    }
}