package com.jgarivera.qwest.auth.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class UsernameTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "juandela1",
            "JuanDelaCruz",
            "juan_dela_cruz",
            "usernamethatisthirtycharacters",
    })
    void it_creates_valid_username(String value) {
        assertThatNoException().isThrownBy(() -> new Username(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1juandela",
            "juandela!",
            "JuaN_del@",
            "usernamethatismorethanthirtycharacters"
    })
    void it_throws_on_invalid_username(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Username(value));
    }

    @Test
    void it_throws_on_empty_username() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Username(null));
    }
}
