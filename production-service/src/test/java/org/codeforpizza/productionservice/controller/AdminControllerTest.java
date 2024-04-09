package org.codeforpizza.productionservice.controller;

import io.restassured.RestAssured;
import org.codeforpizza.productionservice.modell.DTOs.ApplicationUser;
import org.junit.jupiter.api.*;
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
class AdminControllerTest {

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
    void getOneUser() {
        given()
                .when()
                .get("/admin/1")
                .then()
                .statusCode(200)
                .body("username", equalTo("admin"));
    }

    @Test
    @Order(2)
    void getAllUsers() {
        given()
                .when()
                .get("/admin/")
                .then()
                .statusCode(200)
                .body("size()", equalTo(2));
    }

    @Test
    @Order(3)
    void updateUser() {
        ApplicationUser user = new ApplicationUser( "Ben Deg", "Buzzword2", null, null);
        given()
                .contentType("application/json")
                .body(user)
                .when()
                .put("/admin/2")
                .then()
                .statusCode(200)
                .body("username", equalTo("Ben Deg"));
    }

    @Test
    @Order(4)
    void deleteUser() {
        given()
                .when()
                .delete("/admin/2")
                .then()
                .statusCode(200)
                .body(equalTo("User deleted successfully"));
    }
}