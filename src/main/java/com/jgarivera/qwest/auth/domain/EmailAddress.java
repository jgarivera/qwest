package com.jgarivera.qwest.auth.domain;

import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record EmailAddress(String value) {

    /**
     * Provided by OWASP.
     *
     * @see <a href="https://owasp.org/www-community/OWASP_Validation_Regex_Repository">OWASP Validation Regex Repository</a>
     */
    public static final String VALID_EMAIL_ADDRESS_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);

    public EmailAddress {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    public static boolean isValid(String value) {
        Assert.hasText(value, "value must have text");

        Matcher matcher = VALID_EMAIL_ADDRESS.matcher(value);
        return matcher.matches();
    }
}
