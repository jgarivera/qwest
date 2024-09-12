package com.jgarivera.qwest.challenges.domain.model;

import com.jgarivera.qwest.shared.application.TestDatabaseConfiguration;
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
class BadgeRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    BadgeRepository repository;

    @Test
    void it_creates_badge() {
        var id = repository.nextId();
        var badge = new Badge(id, "Badge title", "Badge description", "https://example.com/image.jpg");

        repository.save(badge);
        entityManager.flush();

        Badge savedBadge = repository.findById(id).orElseThrow();

        assertThat(savedBadge).isEqualTo(badge);
        assertThat(savedBadge.getTitle()).isEqualTo("Badge title");
        assertThat(savedBadge.getDescription()).isEqualTo("Badge description");
        assertThat(savedBadge.getImageUrl()).isEqualTo("https://example.com/image.jpg");
    }
}
