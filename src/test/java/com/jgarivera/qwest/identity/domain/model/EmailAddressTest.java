package com.jgarivera.qwest.identity.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class EmailAddressTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "username@domain.com",
            "user.name@domain.com",
            "user-name@domain.com",
            "username@domain.com.ph",
            "user_name@domain.com"
    })
    void it_creates_valid_email_address(String value) {
        assertThatNoException().isThrownBy(() -> new EmailAddress(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "username.@domain.com",
            ".user.name@domain.com",
            "user-name@domain.com.",
            "username@.com",
    })
    void it_throws_on_invalid_email_address(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new EmailAddress(value));
    }

    @Test
    void it_throws_on_empty_email_address() {
        assertThatIllegalArgumentException().isThrownBy(() -> new EmailAddress(null));
    }
}
