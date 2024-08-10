package com.jgarivera.qwest.auth.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class UserIdTest {

    @Test
    void it_creates_user_id() {
        var id = UUID.fromString("00000000-0000-0000-0000-000000000000");
        assertThat(new UserId(id).id()).isEqualTo(id);
    }

    @Test
    void it_validates_user_id() {
        assertThatIllegalArgumentException().isThrownBy(() -> new UserId(null));
    }
}
