package com.jgarivera.qwest;

import com.jgarivera.qwest.auth.domain.EmailAddress;
import com.jgarivera.qwest.auth.domain.PersonalName;
import com.jgarivera.qwest.auth.domain.User;
import com.jgarivera.qwest.auth.domain.UserId;
import com.jgarivera.qwest.auth.domain.Username;
import com.jgarivera.qwest.shared.UUIDFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@TestConfiguration
@Import({TestUUIDFactoryConfiguration.class, SecurityConfiguration.class})
public class TestSecurityConfiguration {

    @Bean
    UserDetailsService userDetailsService(UUIDFactory uuidFactory, PasswordEncoder passwordEncoder) {
        List<UserDetails> users = List.of(new User(
                new UserId(uuidFactory.create()),
                new PersonalName("First", "Middle", "Last"),
                new EmailAddress("testuser@example.com"),
                new Username("testuser"),
                passwordEncoder.encode("password")
        ));

        return new InMemoryUserDetailsManager(users);
    }
}
