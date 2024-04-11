package org.codeforpizza.productionservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@TestConfiguration(proxyBeanMethods = false)
class ProductionServiceApplicationTests {

    @Bean
    @ServiceConnection
    MySQLContainer<?> mysqlContainer() {
        return new MySQLContainer<>(DockerImageName.parse("mysql:latest")).withDatabaseName("productions").withUsername("root").withPassword("root");
    }

    public static void main(String[] args) {
        SpringApplication.from(ProductionServiceApplicationTests::main).with(ProductionServiceApplicationTests.class).run(args);
    }

}
