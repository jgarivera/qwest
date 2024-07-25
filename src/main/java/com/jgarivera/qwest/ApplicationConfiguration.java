package com.jgarivera.qwest;

import com.github.f4b6a3.uuid.UuidCreator;
import com.jgarivera.qwest.shared.UUIDFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApplicationConfiguration {

    @Bean
    UUIDFactory uuidFactory() {
        return UuidCreator::getTimeOrderedEpoch;
    }
}
