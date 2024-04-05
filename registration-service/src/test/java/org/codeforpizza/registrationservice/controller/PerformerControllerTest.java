package org.codeforpizza.registrationservice.controller;

import io.restassured.RestAssured;
import org.codeforpizza.registationservice.repository.UserRepository;
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
    void getPerformer() {
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .get("/performer")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("John"));
    }

    @Test
    @Order(2)
    void updatePerformer() {
        given()
                .contentType("application/json")
                .headers("Authorization", "Bearer " + token)
                .body("""
                        {
                            "firstName": "updated-Name",
                            "lastName": "updated-Name",
                            "email": "updated-Name",
                            "phoneNr": "updated-Name",
                            "department": "updated-Name"
                        }""")
                .when()
                .put("/performer")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("updated-Name"));
    }

    @Test
    @Order(3)
    void updateMeasurements() {
        given()
                .contentType("application/json")
                .headers("Authorization", "Bearer " + token)
                .body("""
                        {
                             "height": 188,
                             "shoeSize": 39,
                             "jacketSize": 50,
                             "pantSize": 50,
                             "head": 67
                         }""")
                .when()
                .put("/performer/measurements/")
                .then()
                .statusCode(200)
                .body("measurements.height", equalTo(188.0f));
    }

    @Test
    @Order(4)
    void deleteAccount() {
        given()
                .headers("Authorization", "Bearer " + token)
                .when()
                .delete("/performer")
                .then()
                .statusCode(200)
                .body(equalTo("Performer deleted successfully"));
    }
}