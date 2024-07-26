package com.jgarivera.qwest;

import com.jgarivera.qwest.shared.UUIDFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@TestConfiguration
public class TestUUIDFactoryConfiguration {

    @Bean
    UUIDFactory uuidFactory() {
        return UUID::randomUUID;
    }
}
