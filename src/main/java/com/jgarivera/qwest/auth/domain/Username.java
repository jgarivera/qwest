package com.jgarivera.qwest.auth.domain;

import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Username(String value) {

    /**
     * First character must be a letter.
     * Allowed characters are letters, numbers, and underscores.
     * Length must be 8 to 30 characters inclusive.
     */
    private static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[A-Za-z][A-Za-z0-9_]{7,29}$");

    public Username {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public static boolean isValid(String value) {
        Assert.hasText(value, "value must have text");

        Matcher matcher = VALID_USERNAME_REGEX.matcher(value);
        return matcher.matches();
    }
}
