package com.jgarivera.qwest.identity.domain.model;

import org.jmolecules.ddd.types.ValueObject;
import org.springframework.util.Assert;

public record PersonalName(String firstName, String middleName, String lastName) implements ValueObject {

    public PersonalName {
        Assert.hasText(firstName, "first name must have text");
        Assert.hasText(lastName, "last name must have text");
    }
}
