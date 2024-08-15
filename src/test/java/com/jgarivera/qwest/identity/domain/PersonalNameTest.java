package com.jgarivera.qwest.identity.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PersonalNameTest {

    @ParameterizedTest
    @CsvSource({
            "Juan,Pedro,Dela Cruz",
            "Pedro,,Gil"
    })
    void it_creates_valid_natural_person_name(String firstName, String middleName, String lastName) {
        assertThatNoException().isThrownBy(() -> new PersonalName(firstName, middleName, lastName));
    }

    @ParameterizedTest
    @CsvSource({
            "Juan,,",
            ",,Dela Cruz",
            "Juan,Pedro,",
            ",Pedro,Dela Cruz"
    })
    void it_throws_on_invalid_email_address(String firstName, String middleName, String lastName) {
        assertThatIllegalArgumentException().isThrownBy(() -> new PersonalName(firstName, middleName, lastName));
    }
}
