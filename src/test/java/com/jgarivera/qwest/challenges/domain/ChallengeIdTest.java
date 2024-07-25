package com.jgarivera.qwest.challenges.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class ChallengeIdTest {

    @Test
    void it_creates_challenge_id() {
        var id = UUID.randomUUID();
        assertThat(new ChallengeId(id).id()).isEqualTo(id);
    }

    @Test
    void it_validates_challenge_id() {
        assertThatIllegalArgumentException().isThrownBy(() -> new ChallengeId(null));
    }
}
