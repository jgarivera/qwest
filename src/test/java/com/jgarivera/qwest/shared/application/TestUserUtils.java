package com.jgarivera.qwest.shared.application;

import com.jgarivera.qwest.identity.domain.model.EmailAddress;
import com.jgarivera.qwest.identity.domain.model.PersonalName;
import com.jgarivera.qwest.identity.domain.model.User;
import com.jgarivera.qwest.identity.domain.model.UserId;
import com.jgarivera.qwest.identity.domain.model.Username;

import java.util.UUID;

public class TestUserUtils {

    public static User createRandom(String firstName, String lastName) {
        String username = firstName.concat(lastName).toLowerCase();

        return User.create(
                new UserId(UUID.randomUUID()),
                new PersonalName(firstName, null, lastName),
                new EmailAddress(username + "@example.com"),
                new Username(username),
                "password"
        );
    }
}
