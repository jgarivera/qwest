package com.jgarivera.qwest.challenges.domain;

import com.jgarivera.qwest.TestDatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestDatabaseConfiguration.class)
class ChallengeRepositoryTest {

    @Autowired
    ChallengeRepository repository;

    @Test
    void it_creates_challenge() {
        var id = new ChallengeId(UUID.randomUUID());
        var challenge = new Challenge(id, "Challenge name", "Challenge description");
        repository.save(challenge);

        Challenge savedChallenge = repository.findById(id).orElseThrow();

        assertThat(savedChallenge).isNotNull();
        assertThat(savedChallenge.getName()).isEqualTo("Challenge name");
        assertThat(savedChallenge.getDescription()).isEqualTo("Challenge description");
    }
}
