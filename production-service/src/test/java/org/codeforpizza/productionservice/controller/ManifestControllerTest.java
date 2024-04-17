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
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManifestControllerTest {

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
    void createManifest() {
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body("""
                        {
                        	"title": "Creating Manifest test",
                            "year": "2005"
                        }
                        """)
                .when()
                .post("/manifests/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void updateManifest() {
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body("""
                        {
                        	"title": "Updating Manifest test",
                            "year": "2005"
                        }
                        """)
                .when()
                .put("/manifests/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    void deleteManifest() {
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/manifests/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    void getManifest() {
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .get("/manifests/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    void getAllManifests() {
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .get("/manifests/production/2")
                .then()
                .statusCode(200);
    }
}