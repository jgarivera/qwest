package com.jgarivera.qwest.identity.domain.model;

import org.jmolecules.ddd.types.Identifier;
import org.springframework.util.Assert;

import java.util.UUID;

public record UserId(UUID id) implements Identifier {

    public UserId {
        Assert.notNull(id, "id must not be null");
    }
}
