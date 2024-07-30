package com.jgarivera.qwest.challenges.domain;

import com.jgarivera.qwest.TestDatabaseConfiguration;
import com.jgarivera.qwest.TestUUIDFactoryConfiguration;
import com.jgarivera.qwest.shared.UUIDFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({TestDatabaseConfiguration.class, TestUUIDFactoryConfiguration.class})
class ChallengeRepositoryTest {

    @Autowired
    UUIDFactory uuidFactory;
    @Autowired
    ChallengeRepository repository;

    @Test
    void it_creates_challenge() {
        var id = new ChallengeId(uuidFactory.create());
        var challenge = new Challenge(id, "Challenge title", "Challenge description");
        repository.save(challenge);

        Challenge savedChallenge = repository.findById(id).orElseThrow();

        assertThat(savedChallenge).isNotNull();
        assertThat(savedChallenge.getTitle()).isEqualTo("Challenge title");
        assertThat(savedChallenge.getDescription()).isEqualTo("Challenge description");
    }
}
