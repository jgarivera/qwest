package com.jgarivera.qwest.challenges.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class BadgeIdTest {

    @Test
    void it_creates_badge_id() {
        var id = UUID.fromString("00000000-0000-0000-0000-000000000000");
        assertThat(new BadgeId(id).id()).isEqualTo(id);
    }

    @Test
    void it_validates_badge_id() {
        assertThatIllegalArgumentException().isThrownBy(() -> new BadgeId(null));
    }
}
