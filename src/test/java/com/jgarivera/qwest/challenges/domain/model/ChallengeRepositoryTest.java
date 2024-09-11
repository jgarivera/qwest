package com.jgarivera.qwest.challenges.domain.model;

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
class ChallengeRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    ChallengeRepository repository;

    @Test
    void it_creates_challenge() {
        var id = repository.nextId();
        var challenge = new Challenge(
                id,
                "Challenge title",
                ChallengeVisibility.PUBLIC,
                "Challenge description"
        );

        repository.save(challenge);
        entityManager.flush();

        Challenge savedChallenge = repository.findById(id).orElseThrow();

        assertThat(savedChallenge).isEqualTo(challenge);
        assertThat(savedChallenge.getTitle()).isEqualTo("Challenge title");
        assertThat(savedChallenge.getDescription()).isEqualTo("Challenge description");
        assertThat(savedChallenge.getVisibility()).isEqualTo(ChallengeVisibility.PUBLIC);
    }
}
