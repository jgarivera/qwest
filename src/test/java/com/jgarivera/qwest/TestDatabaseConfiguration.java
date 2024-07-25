package com.jgarivera.qwest;

import com.jgarivera.qwest.shared.UUIDFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.UUID;

@TestConfiguration
public class TestDatabaseConfiguration {

    @Bean
    UUIDFactory testUUIDFactory() {
        return UUID::randomUUID;
    }

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>(
                "postgres:16.3-alpine3.20"
        );
    }
}
