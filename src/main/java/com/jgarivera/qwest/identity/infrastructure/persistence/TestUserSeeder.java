package com.jgarivera.qwest.identity.infrastructure.persistence;

import com.jgarivera.qwest.identity.domain.model.EmailAddress;
import com.jgarivera.qwest.identity.domain.model.PersonalName;
import com.jgarivera.qwest.identity.domain.model.User;
import com.jgarivera.qwest.identity.domain.model.UserRepository;
import com.jgarivera.qwest.identity.domain.model.Username;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
class TestUserSeeder implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(TestUserSeeder.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    TestUserSeeder(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var username = new Username("juandelacruz");

        if (repository.existsByUsername(username)) {
            logger.debug("Test user seeder not executed because user already exists");
            return;
        }

        var user = User.create(
                repository.nextId(),
                new PersonalName("Juan", "Pedro", "Dela Cruz"),
                new EmailAddress("juan@example.com"),
                username,
                passwordEncoder.encode("password")
        );

        repository.save(user);

        logger.info("Test user seeder executed because user does not exist");
    }
}
