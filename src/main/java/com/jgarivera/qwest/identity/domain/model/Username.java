package com.jgarivera.qwest.identity.domain.model;

import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Username(String value) {

    /**
     * First character must be a letter.
     * Allowed characters are letters, numbers, and underscores.
     * Length must be 8 to 30 characters inclusive.
     */
    public static final String VALID_USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9_]{7,29}$";

    private static final Pattern VALID_USERNAME = Pattern.compile(VALID_USERNAME_REGEX);

    public Username {
        Assert.hasText(value, "value must have text");

        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public static boolean isValid(String value) {
        Matcher matcher = VALID_USERNAME.matcher(value);
        return matcher.matches();
    }
}
