package com.jgarivera.qwest.identity.domain;

import com.jgarivera.qwest.TestDatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestDatabaseConfiguration.class)
class UserRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    UserRepository repository;

    @Test
    void it_creates_user() {
        // Password encoding is irrelevant in this test
        var user = new User(
                repository.nextId(),
                new PersonalName("Juan", "Pedro", "Dela Cruz"),
                new EmailAddress("juan@example.com"),
                new Username("juandelacruz"),
                "password"
        );

        repository.save(user);
        entityManager.flush();

        var savedUser = repository.findByEmail(new EmailAddress("juan@example.com"));

        assertThat(savedUser).isEqualTo(user);
        assertThat(savedUser.getName()).isEqualTo(user.getName());
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
    }
}
