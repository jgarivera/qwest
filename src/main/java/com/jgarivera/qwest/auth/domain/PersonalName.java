package com.jgarivera.qwest.auth.domain;

import org.springframework.util.Assert;

public record PersonalName(String firstName, String middleName, String lastName) {

    public PersonalName {
        Assert.hasText(firstName, "first name must have text");
        Assert.hasText(lastName, "last name must have text");
    }
}
