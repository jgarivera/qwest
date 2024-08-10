package com.jgarivera.qwest.auth.infrastructure;

import com.jgarivera.qwest.auth.domain.EmailAddress;
import com.jgarivera.qwest.auth.domain.PersonalName;
import com.jgarivera.qwest.auth.domain.User;
import com.jgarivera.qwest.auth.domain.UserId;
import com.jgarivera.qwest.auth.domain.UserRepository;
import com.jgarivera.qwest.auth.domain.Username;
import com.jgarivera.qwest.shared.UUIDFactory;
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
    private final UUIDFactory uuidFactory;
    private final PasswordEncoder passwordEncoder;

    TestUserSeeder(UserRepository repository, UUIDFactory uuidFactory, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.uuidFactory = uuidFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var username = new Username("juandelacruz");

        if (repository.existsByUsername(username)) {
            logger.debug("Test user seeder not executed because user already exists");
            return;
        }

        var user = new User(
                new UserId(uuidFactory.create()),
                new PersonalName("Juan", "Pedro", "Dela Cruz"),
                new EmailAddress("juan@example.com"),
                username,
                passwordEncoder.encode("password")
        );

        repository.save(user);

        logger.info("Test user seeder executed because user does not exist");
    }
}
