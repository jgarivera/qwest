package com.jgarivera.qwest;

import com.github.f4b6a3.uuid.UuidCreator;
import com.jgarivera.qwest.identity.domain.EmailAddress;
import com.jgarivera.qwest.identity.domain.PersonalName;
import com.jgarivera.qwest.identity.domain.User;
import com.jgarivera.qwest.identity.domain.UserId;
import com.jgarivera.qwest.identity.domain.Username;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@TestConfiguration
@Import(SecurityConfiguration.class)
public class TestSecurityConfiguration {

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        List<UserDetails> users = List.of(User.create(
                new UserId(UuidCreator.getTimeOrderedEpoch()),
                new PersonalName("First", "Middle", "Last"),
                new EmailAddress("testuser@example.com"),
                new Username("testuser"),
                passwordEncoder.encode("password")
        ));

        return new InMemoryUserDetailsManager(users);
    }
}
