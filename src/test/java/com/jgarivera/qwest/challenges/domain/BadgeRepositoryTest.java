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
class BadgeRepositoryTest {

    @Autowired
    UUIDFactory uuidFactory;
    @Autowired
    BadgeRepository repository;

    @Test
    void it_creates_badge() {
        var id = new BadgeId(uuidFactory.create());
        var badge = new Badge(id, "Badge title", "Badge description", "https://example.com/image.jpg");
        repository.save(badge);

        Badge savedBadge = repository.findById(id).orElseThrow();

        assertThat(savedBadge).isEqualTo(badge);
        assertThat(savedBadge.getTitle()).isEqualTo("Badge title");
        assertThat(savedBadge.getDescription()).isEqualTo("Badge description");
        assertThat(savedBadge.getImageUrl()).isEqualTo("https://example.com/image.jpg");
    }
}
