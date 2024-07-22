package com.jgarivera.qwest.challenges.domain;

import com.jgarivera.qwest.TestDatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestDatabaseConfiguration.class)
class ChallengeRepositoryTest {

    @Autowired
    ChallengeRepository repository;

    @Test
    void it_creates_challenge() {
        var challenge = new Challenge(1L, "Challenge name", "Challenge description");
        repository.save(challenge);

        Challenge savedUser = repository.findById(1L).orElseThrow();

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("Challenge name");
        assertThat(savedUser.getDescription()).isEqualTo("Challenge description");
    }
}
